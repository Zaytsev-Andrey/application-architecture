package ru.geekbrains.network;

import ru.geekbrains.network.request.HttpRequest;
import ru.geekbrains.network.session.Session;

import java.nio.file.Path;

public interface NetworkService {

    HttpRequest readRequest();

    void writeResponse(Session session, String view, HttpStatus status);

    void close();
}
