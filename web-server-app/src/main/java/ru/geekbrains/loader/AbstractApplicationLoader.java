package ru.geekbrains.loader;

import ru.geekbrains.handler.HandlerManager;
import ru.geekbrains.loader.config.Config;
import ru.geekbrains.loader.config.ConfigFactory;
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

    public void load(String[] args) {
        try {
            Config config = loadProperties(args);
            ControllerMapper controllerMapper = loadRequestControllersToControllerMapper();
            TemplateResolver templateResolver = new FileTemplateResolver(config.getTemplatePrefix(),
                    config.getTemplateSuffix());
            HandlerManager handlerManager = loadSocketHandlerManager(controllerMapper, templateResolver,
                    config.getHttpVersion());
            Server server = loadServer(config.getServerPort(), handlerManager);
            server.start();
        } catch (IOException e) {
            System.out.println("Web Server was not loaded");
            e.printStackTrace();
        }
    }

    public Config loadProperties(String[] args) throws IOException {
        return ConfigFactory.create(args);
    }

    abstract public ControllerMapper loadRequestControllersToControllerMapper();

    abstract public HandlerManager loadSocketHandlerManager(ControllerMapper controllerMapper,
                                                            TemplateResolver templateResolver,
                                                            String httpVersion);

    abstract public Server loadServer(int port, HandlerManager handlerManager);
}
