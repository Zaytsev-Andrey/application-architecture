package ru.geekbrains.handler;

import java.net.Socket;

public interface HandlerManager {

    Runnable newSocketHandler(Socket socket);
}
