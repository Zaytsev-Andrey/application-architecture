package ru.geekbrains.network.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс для хранения заголовков запроса
 */
public class HttpHeaders {

    private Map<String, List<String>> headers = new HashMap<>();

    public HttpHeaders() {

    }

    public HttpHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public void add(String header, String value) {
        List<String> values = headers.getOrDefault(header, new ArrayList<>());
        values.add(value);
        headers.put(header, values);
    }

    @Override
    public String toString() {
        StringBuilder headerBuilder = new StringBuilder();
        for (Map.Entry<String, List<String>> header : headers.entrySet()) {
            headerBuilder.append(header.getKey()).append(": ");
            headerBuilder.append(String.join("; ", header.getValue()));
            headerBuilder.append("\n");
        }
        return headerBuilder.toString();
    }
}
