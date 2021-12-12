package ru.geekbrains.network.request;

import ru.geekbrains.network.session.Session;

/**
 * Класс для хранения HTTP запроса
 */
public class HttpRequest {

    private String method;

    private String url;

    private HttpHeaders headers;

    private HttpCookies cookies;

    private String body;

    private Session session;

    public static class HttpRequestBuilder {

        private String method;

        private String url;

        private HttpHeaders headers;

        private HttpCookies cookies;

        private String body;

        private Session session;

        public HttpRequestBuilder() {

        }

        public HttpRequestBuilder method(String method) {
            this.method = method;
            return this;
        }

        public HttpRequestBuilder url(String url) {
            this.url = url;
            return  this;
        }

        public HttpRequestBuilder headers(HttpHeaders headers) {
            this.headers = headers;
            return this;
        }

        public HttpRequestBuilder cookies(HttpCookies cookies) {
            this.cookies = cookies;
            return this;
        }

        public HttpRequestBuilder body(String body) {
            this.body = body;
            return this;
        }

        public HttpRequestBuilder session(Session session) {
            this.session = session;
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }

    private HttpRequest(HttpRequestBuilder builder) {
        this.method = builder.method;
        this.url = builder.url;
        this.headers = builder.headers;
        this.cookies = builder.cookies;
        this.body = builder.body;
        this.session = builder.session;
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
