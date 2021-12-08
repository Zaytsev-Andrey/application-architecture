package ru.geekbrains.resolver;

import java.nio.file.Path;

public interface TemplateResolver {

    Path resolveTemplatePathByName(String name);
}
