package ru.geekbrains.view;

import java.nio.file.Path;

public interface TemplateEngine {

    String getView(Path templatePath);
}
