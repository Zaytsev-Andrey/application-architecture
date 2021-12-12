package ru.geekbrains.network;

import ru.geekbrains.network.io.*;
import ru.geekbrains.network.request.HttpRequest;
import ru.geekbrains.network.session.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/**
 * Реализация NetworkService для чтения из сетевого потока и записи в сетевой поток
 */
public class HttpNetworkService implements NetworkService {

    private Socket socket;

    private RequestManager requestManager;

    private ResponseManager responseManager;

    public HttpNetworkService(Socket socket, RequestManager requestManager, ResponseManager responseManager) {
        this.socket = socket;
        this.requestManager = requestManager;
        this.responseManager = responseManager;
    }

    public HttpRequest readRequest() {
        HttpRequestDto requestDto = new HttpRequestDto();

        try {
            BufferedRequestReader requestReader = new BufferedRequestReader(
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream(), StandardCharsets.UTF_8)));
            HttpRequestDtoReader requestDtoReader = new HttpRequestDtoReader(requestReader);
            requestDto = requestDtoReader.readRequestDto();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return requestManager.newHttpRequest(requestDto);
    }

    @Override
    public void writeResponse(Session session, Path templatePath, HttpStatus status) {

        try {
            HttpResponseWriter responseWriter = new HttpResponseWriter(new PrintWriter(socket.getOutputStream()));
            responseWriter.write(responseManager.newHttpResponse(session, templatePath, status));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(templatePath);
    }

    @Override
    public void close() {
        try {
            socket.close();
            System.out.println("Client disconnected!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
