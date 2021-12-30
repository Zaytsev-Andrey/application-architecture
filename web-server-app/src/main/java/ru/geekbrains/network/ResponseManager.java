package ru.geekbrains.network;

import ru.geekbrains.network.response.HttpResponse;
import ru.geekbrains.network.session.Session;

import java.nio.file.Path;

public interface ResponseManager {

    HttpResponse newHttpResponse(Session session, String view, HttpStatus status);
}
