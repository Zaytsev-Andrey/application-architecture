package ru.geekbrains.network;

import ru.geekbrains.network.response.HttpContentType;
import ru.geekbrains.network.response.HttpResponse;
import ru.geekbrains.network.session.Session;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Фабричный класс для создания экземпляров класса HttpResponse
 */
public class HttpResponseManager implements ResponseManager {

    @Override
    public HttpResponse newHttpResponse(Session session, Path templatePath, HttpStatus status) {
        HttpResponse httpResponse = null;

        try {
            httpResponse = new HttpResponse.HttpResponseBuilder()
                    .status(status)
                    .contentType(new HttpContentType())
                    .body(String.join("\n", Files.readAllLines(templatePath)))
                    .setCookies(session.getNewCookies())
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }
}
