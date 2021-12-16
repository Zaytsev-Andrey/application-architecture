package ru.geekbrains.server;

import ru.geekbrains.handler.HandlerManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Реализация интерфейса Server с использованием классов сетевого взаимодействия ServerSocket и Socket
 */
public class SocketServer implements Server {

    private final ExecutorService service = Executors.newCachedThreadPool();

    private final HandlerManager handlerManager;

    private final int port;

    public SocketServer(int port, HandlerManager handlerManager) {
        this.port = port;
        this.handlerManager = handlerManager;
    }

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

                service.submit(handlerManager.newSocketHandler(socket));
//                new Thread(() -> handleRequest(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            stop();
        }
    }

    @Override
    public void stop() {
        service.shutdown();
    }
}
