package ru.geekbrains.network.request;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс для хранения  Cookies
 */
public class HttpCookies {

    private Map<String, String> cookies = new HashMap<>();

    public HttpCookies() {
    }

    public HttpCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public String getCookieValueByKey(String key) {
        return cookies.get(key);
    }

    public void add(String key, String value) {
        cookies.put(key, value);
    }

    @Override
    public String toString() {
        return cookies.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("; "));
    }
}
