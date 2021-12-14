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
        printWriter.print(response.getVersion());
        printWriter.print(" ");
        printWriter.println(response.getStatus());
        printWriter.print(response.getHeaders());
        printWriter.println(response.getSetCookies());
        printWriter.println();
        printWriter.println(response.getBody());
        printWriter.flush();
    }

    @Override
    public void close() throws Exception {
        printWriter.close();
    }
}
