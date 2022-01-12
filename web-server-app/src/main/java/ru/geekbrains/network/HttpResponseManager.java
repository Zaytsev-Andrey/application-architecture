package ru.geekbrains.network;

import ru.geekbrains.network.request.HttpHeaders;
import ru.geekbrains.network.response.HttpResponse;
import ru.geekbrains.network.session.Session;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Фабричный класс для создания экземпляров класса HttpResponse
 */
public class HttpResponseManager implements ResponseManager {

    private final String httpVersion;

    public HttpResponseManager(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    @Override
    public HttpResponse newHttpResponse(Session session, String view, HttpStatus status) {
        HttpResponse httpResponse = null;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html");
        headers.add("Content-Type", "charset=utf-8");

        httpResponse = new HttpResponse.Builder()
                .withVersion(httpVersion)
                .withStatus(status)
                .withHeaders(headers)
                .withBody(view)
                .withSetCookies(session.getNewCookies())
                .build();

        return httpResponse;
    }
}
