package ru.geekbrains.server;

import ru.geekbrains.handler.SocketHandlerManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Реализация интерфейса Server с использованием классов сетевого взаимодействия ServerSocket и Socket
 */
public class SocketServer implements Server {

    private ExecutorService service = Executors.newCachedThreadPool();

    private SocketHandlerManager handlerManager;

    public SocketServer(SocketHandlerManager handlerManager) {
        this.handlerManager = handlerManager;
    }

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

                service.submit(handlerManager.getSocketHandler(socket));
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
