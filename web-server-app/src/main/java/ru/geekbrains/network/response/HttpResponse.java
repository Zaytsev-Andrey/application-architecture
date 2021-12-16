package ru.geekbrains.network.response;

import ru.geekbrains.network.HttpStatus;
import ru.geekbrains.network.request.HttpCookies;
import ru.geekbrains.network.request.HttpHeaders;

/**
 * Класс для хранения HTTP ответа
 */
public class HttpResponse {

    private String version;

    private HttpStatus status;

    private HttpHeaders headers;

    private String body;

    private HttpCookies setCookies;

    public static class Builder {

        private final HttpResponse httpResponse;

        public Builder() {
            this.httpResponse = new HttpResponse();
        }

        public Builder withVersion(String version) {
            httpResponse.version = version;
            return this;
        }

        public Builder withStatus(HttpStatus status) {
            httpResponse.status = status;
            return this;
        }

        public Builder withHeaders(HttpHeaders headers) {
            httpResponse.headers = headers;
            return this;
        }

        public Builder withBody(String body) {
            httpResponse.body = body;
            return this;
        }

        public Builder withSetCookies(HttpCookies setCookies) {
            httpResponse.setCookies = setCookies;
            return this;
        }

        public HttpResponse build() {
            return httpResponse;
        }
    }

    private HttpResponse() {

    }

    public String getVersion() {
        return version;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public HttpCookies getSetCookies() {
        return setCookies;
    }
}
