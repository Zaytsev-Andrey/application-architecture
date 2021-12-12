package ru.geekbrains.network.io;

import ru.geekbrains.network.response.HttpResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Декоратор PrintWriter для записи объекта HttpResponse в сетевой поток
 */
public class HttpResponseWriter implements AutoCloseable {

    private PrintWriter printWriter;

    public HttpResponseWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void write(HttpResponse response) throws IOException {
        printWriter.println("HTTP/1.1 " + response.getStatus());
        printWriter.println("Content-Type: " + response.getContentType());
        printWriter.println("Set-Cookie: " + response.getSetCookies());
        printWriter.println();
        printWriter.println(response.getBody());
        printWriter.flush();
    }

    @Override
    public void close() throws Exception {
        printWriter.close();
    }
}
