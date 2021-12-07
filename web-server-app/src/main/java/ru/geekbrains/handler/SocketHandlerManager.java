package ru.geekbrains.handler;

import java.net.Socket;

public interface SocketHandlerManager {

    Runnable getSocketHandler(Socket socket);
}
