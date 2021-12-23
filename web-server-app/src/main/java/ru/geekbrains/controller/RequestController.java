package ru.geekbrains.controller;

public interface RequestController {

    String doGet();

    default String doPost() {
        throw new UnsupportedOperationException();
    }

    default String doPut() {
        throw new UnsupportedOperationException();
    }

    default String doDelete() {
        throw new UnsupportedOperationException();
    }
}
