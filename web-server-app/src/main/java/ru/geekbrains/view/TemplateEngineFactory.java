package ru.geekbrains.view;

import ru.geekbrains.model.Model;

public class TemplateEngineFactory {

    public static TemplateEngine createTemplateEngine(Model model) {
        return new HtmlTemplateEngine(model);
    }
}
