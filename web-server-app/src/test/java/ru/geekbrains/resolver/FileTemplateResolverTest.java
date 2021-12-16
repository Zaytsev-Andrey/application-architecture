package ru.geekbrains.resolver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileTemplateResolverTest {

    private static String prefix;
    private static String suffix;

    @BeforeAll
    public static void init() {
        prefix = "/home/andrey/projects/geekbrains/level8/application-architecture/web-server-app/src/test/resource/templates";
        suffix = ".html";
    }

    @Test
    public void templateResolverTest() {
        TemplateResolver templateResolver = new FileTemplateResolver(prefix, suffix);

        Path homeTemplate = templateResolver.resolveTemplatePathByName("home");
        Assertions.assertTrue(Files.exists(homeTemplate));
        Assertions.assertTrue(Files.isRegularFile(homeTemplate));
    }
}
