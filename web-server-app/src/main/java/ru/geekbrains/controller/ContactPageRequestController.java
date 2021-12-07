package ru.geekbrains.controller;

import ru.geekbrains.mapper.RequestMappingController;

/**
 * Реализация интерфейса RequestController обрабатывающая запрос домашней страницы и возвращающая соответствующее ей
 * представление
 */
@RequestMappingController(path = "/contact")
public class ContactPageRequestController implements RequestController {

    private static final String TEMPLATE_NAME = "contact";

    @Override
    public String handleRequest() {
        // TODO business logic and model filling
        return TEMPLATE_NAME;
    }
}
