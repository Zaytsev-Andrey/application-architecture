package ru.geekbrains.network.io;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Класс для преобразования входящего потока в объект Java
 */
public class HttpRequestDto {

    private String method;

    private String url;

    private String version;

    private Map<String, List<String>> headers;

    private Map<String, String> cookies;

    private String body;


    public HttpRequestDto() {
        headers = new HashMap<>();
        cookies = new HashMap<>();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public Optional<String> getCookieByName(String name) {
        return Optional.ofNullable(cookies.get(name));
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
