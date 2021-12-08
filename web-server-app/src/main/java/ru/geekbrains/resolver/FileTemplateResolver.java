package ru.geekbrains.resolver;

import java.nio.file.Path;

/**
 * Реализация интерфейса TemplateResolver использующая для разрешения имени шаблона prefix и suffix.
 * Prefix - путь к файлу шаблона.
 * Suffix - расширение файла.
 */
public class FileTemplateResolver implements TemplateResolver {

    private String prefix;

    private String suffix;

    public FileTemplateResolver(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public Path resolveTemplatePathByName(String name) {
        return Path.of(prefix, name + suffix);
    }
}
