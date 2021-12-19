<h1>Применение одного из паттернов в коде веб-сервера</h1>

<p>Очень близкими к класической реализации паттерна Декоратор являются два класса используемые в коде веб-сервера:</p>
<ul>
    <li>BufferedRequestReader</li>
    <li>HttpRequestDtoReader</li>
</ul>

<p>Класс <b>BufferedRequestReader</b> является оберткой к классу <b>BufferedReader</b> и расширяет его функционал 
позволяя считать сразу все строки http запроса:</p>

<pre>
    <code>
        public class BufferedRequestReader implements AutoCloseable {
    
            private BufferedReader bufferedReader;
        
            public BufferedRequestReader(BufferedReader bufferedReader) {
                this.bufferedReader = bufferedReader;
            }
        
            public Deque<String> readAllLines() throws IOException {
                while (!bufferedReader.ready());
        
                Deque<String> request = new LinkedList<>();
                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    System.out.println(line);
                    request.add(line);
                }
                return request;
            }
        
            @Override
            public void close() throws Exception {
                bufferedReader.close();
            }
        }
    </code>
</pre>

<p>Класс <b>HttpRequestDtoReader</b> в свою очередь оборачиват уже класс <b>BufferedRequestReader</b> расширяя его 
функционал до возможности чтения DTO объектов:</p>

<pre>
    <code>
        public class HttpRequestDtoReader {
    
            private BufferedRequestReader requestReader;
        
            public HttpRequestDtoReader(BufferedRequestReader requestReader) {
                this.requestReader = requestReader;
            }
        
            public HttpRequestDto readRequestDto() throws IOException {
                HttpRequestDto requestDto = new HttpRequestDto();
        
                Deque<String> requestLines = requestReader.readAllLines();
        
                // Parse method, url and version
                String[] firstLine = requestLines.pollFirst().split(" ");
                requestDto.setMethod(firstLine[0]);
                requestDto.setUrl(firstLine[1]);
                requestDto.setVersion(firstLine[2]);
        
                //Parse headers and cookies
                while (!requestLines.isEmpty()) {
                    String line = requestLines.pollFirst();
                    if (line.isEmpty()) {
                        break;
                    }
                    String[] header = line.split(": ");
                    if ("Cookie".equals(header[0])) {
                        requestDto.setCookies(
                                Arrays.stream(header[1].split("; "))
                                .map(cookie -> cookie.split("="))
                                .collect(toMap(key -> key[0], value -> value[1]))
                        );
                    }
                    List<String> values = new ArrayList<>();
                    values.add(header[1]);
                    requestDto.getHeaders().put(header[0], values);
                }
        
                //Parse body
                StringBuilder body = new StringBuilder();
                while (!requestLines.isEmpty()) {
                    body.append(requestLines.pollFirst());
                }
                requestDto.setBody(body.toString());
        
                return requestDto;
            }
        }
    </code>
</pre>