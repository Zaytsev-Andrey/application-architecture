package ru.geekbrains.loader;

import ru.geekbrains.handler.SocketHandlerManager;
import ru.geekbrains.mapper.ControllerMapper;
import ru.geekbrains.resolver.FileTemplateResolver;
import ru.geekbrains.resolver.TemplateResolver;
import ru.geekbrains.server.Server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Шаблонный класс описываюший последовательность создания экземпляровклассов и установки зависимостей между ними
 */
abstract public class AbstractApplicationLoader {

    public final String propertyFile = "application.properties";

    public void load() {
        try {
            Properties properties = loadProperties();
            ControllerMapper controllerMapper = loadRequestControllersToControllerMapper();
            TemplateResolver templateResolver = new FileTemplateResolver(properties.getProperty("template.prefix"),
                    properties.getProperty("template.suffix"));
            SocketHandlerManager handlerManager = loadSocketHandlerManager(controllerMapper, templateResolver);
            Server server = loadServer(handlerManager);
            server.start();
        } catch (IOException e) {
            System.out.println("Web Server was not loaded");
            e.printStackTrace();
        }
    }

    public Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFile);
        properties.load(inputStream);
        return properties;
    }

    abstract public ControllerMapper loadRequestControllersToControllerMapper();

    abstract public SocketHandlerManager loadSocketHandlerManager(ControllerMapper controllerMapper,
                                                                  TemplateResolver templateResolver);

    abstract public Server loadServer(SocketHandlerManager handlerManager);
}
