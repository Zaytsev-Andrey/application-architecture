package ru.geekbrains.controller;

import ru.geekbrains.model.Model;

public interface RequestController {

    String doGet(Model model);

    default String doPost(Model model) {
        throw new UnsupportedOperationException();
    }

    default String doPut(Model model) {
        throw new UnsupportedOperationException();
    }

    default String doDelete(Model model) {
        throw new UnsupportedOperationException();
    }
}
