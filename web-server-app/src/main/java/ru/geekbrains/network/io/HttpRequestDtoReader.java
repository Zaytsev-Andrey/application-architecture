package ru.geekbrains.network.io;

import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toMap;

/**
 * Декоратор BufferedRequestReader для преобразования строк запроса в DTO объект.
 */
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
