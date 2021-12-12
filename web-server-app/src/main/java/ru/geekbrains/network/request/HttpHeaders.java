package ru.geekbrains.network.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Класс для хранения заголовков запроса
 */
public class HttpHeaders {

    private Map<String, List<String>> headers;

    public HttpHeaders() {
    }

    public HttpHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public void addHeader(String header, String value) {
        List<String> values = headers.getOrDefault(header, new ArrayList<>());
        values.add(value);
        headers.put(header, values);
    }
}
