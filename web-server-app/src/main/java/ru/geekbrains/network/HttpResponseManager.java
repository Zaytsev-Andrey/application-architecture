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
    public HttpResponse newHttpResponse(Session session, Path templatePath, HttpStatus status) {
        HttpResponse httpResponse = null;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html");
        headers.add("Content-Type", "charset=utf-8");

        try {
            httpResponse = new HttpResponse.Builder()
                    .withVersion(httpVersion)
                    .withStatus(status)
                    .withHeaders(headers)
                    .withBody(String.join("\n", Files.readAllLines(templatePath)))
                    .withSetCookies(session.getNewCookies())
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }
}
