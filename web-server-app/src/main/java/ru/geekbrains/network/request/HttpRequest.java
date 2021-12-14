package ru.geekbrains.network.request;

import ru.geekbrains.network.session.Session;

/**
 * Класс для хранения HTTP запроса
 */
public class HttpRequest {

    private String method;

    private String url;

    private String version;

    private HttpHeaders headers;

    private HttpCookies cookies;

    private String body;

    private Session session;

    public static class Builder {

        private final HttpRequest httpRequest;

        public Builder() {
            this.httpRequest = new HttpRequest();
        }

        public Builder withMethod(String method) {
            httpRequest.method = method;
            return this;
        }

        public Builder withUrl(String url) {
            httpRequest.url = url;
            return  this;
        }

        public Builder withVersion(String version) {
            httpRequest.version = version;
            return this;
        }

        public Builder withHeaders(HttpHeaders headers) {
            httpRequest.headers = headers;
            return this;
        }

        public Builder withCookies(HttpCookies cookies) {
            httpRequest.cookies = cookies;
            return this;
        }

        public Builder withBody(String body) {
            httpRequest.body = body;
            return this;
        }

        public Builder withSession(Session session) {
            httpRequest.session = session;
            return this;
        }

        public HttpRequest build() {
            return httpRequest;
        }
    }

    private HttpRequest() {

    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public HttpCookies getCookies() {
        return cookies;
    }

    public String getBody() {
        return body;
    }

    public Session getSession() {
        return session;
    }
}
