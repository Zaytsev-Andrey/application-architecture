package ru.geekbrains.controller;

import ru.geekbrains.mapper.RequestMappingController;
import ru.geekbrains.model.Model;

/**
 * Реализация интерфейса RequestController обрабатывающая запрос домашней страницы и возвращающая соответствующее ей
 * представление
 */
@RequestMappingController(path = "/contact")
public class ContactPageRequestController implements RequestController {

    private static final String TEMPLATE_NAME = "contact";

    @Override
    public String doGet(Model model) {
        model.addAttribute("name", "Admin");
        model.addAttribute("phone", "(999)555-55-55");
        model.addAttribute("email", "admin@mail.ru");

        return TEMPLATE_NAME;
    }
}
