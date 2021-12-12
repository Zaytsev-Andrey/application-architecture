package ru.geekbrains.network.response;

import ru.geekbrains.network.HttpStatus;
import ru.geekbrains.network.request.HttpCookies;
import ru.geekbrains.network.request.HttpHeaders;

/**
 * Класс для хранения HTTP ответа
 */
public class HttpResponse {

    private HttpStatus status;

    private HttpContentType contentType;

    private HttpHeaders headers;

    private String body;

    private HttpCookies setCookies;

    public static class HttpResponseBuilder {

        private HttpStatus status;

        private HttpContentType contentType;

        private String body;

        private HttpCookies setCookies;

        public HttpResponseBuilder() {
        }

        public HttpResponseBuilder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public HttpResponseBuilder contentType(HttpContentType contentType) {
            this.contentType = contentType;
            return this;
        }

        public HttpResponseBuilder body(String body) {
            this.body = body;
            return this;
        }

        public HttpResponseBuilder setCookies(HttpCookies setCookies) {
            this.setCookies = setCookies;
            return this;
        }

        public HttpResponse build() {
            return new HttpResponse(this);
        }
    }

    private HttpResponse(HttpResponseBuilder builder) {
        this.status = builder.status;
        this.contentType = builder.contentType;
        this.body = builder.body;
        this.setCookies = builder.setCookies;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public HttpContentType getContentType() {
        return contentType;
    }

    public String getBody() {
        return body;
    }

    public HttpCookies getSetCookies() {
        return setCookies;
    }
}
