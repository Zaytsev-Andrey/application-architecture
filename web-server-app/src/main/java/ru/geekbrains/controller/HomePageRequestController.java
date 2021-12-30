package ru.geekbrains.controller;

import ru.geekbrains.mapper.RequestMappingController;
import ru.geekbrains.model.Model;

/**
 * Реализация интерфейса RequestController обрабатывающая запрос домашней страницы и возвращающая соответствующее ей
 * представление
 */
@RequestMappingController(path = "/")
public class HomePageRequestController implements RequestController {

    private static final String TEMPLATE_NAME = "home";

    @Override
    public String doGet(Model model) {
        // TODO business logic and model filling
        return TEMPLATE_NAME;
    }
}
