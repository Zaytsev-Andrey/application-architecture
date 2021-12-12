package ru.geekbrains.network.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Декоратор BufferedReader для чтения всех строк запроса
 */
public class BufferedRequestReader implements AutoCloseable {

    private BufferedReader bufferedReader;

    public BufferedRequestReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public Deque<String> readAllLines() throws IOException {
        while (!bufferedReader.ready());

        Deque<String> request = new LinkedList<>();
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            System.out.println(line);
            request.add(line);
        }
        return request;
    }

    @Override
    public void close() throws Exception {
        bufferedReader.close();
    }
}
