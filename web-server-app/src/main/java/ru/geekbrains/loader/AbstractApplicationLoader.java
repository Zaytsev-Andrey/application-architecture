package ru.geekbrains.loader;

import ru.geekbrains.handler.HandlerManager;
import ru.geekbrains.handler.method_handler.MethodHandler;
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
public abstract class AbstractApplicationLoader {

    public void load(String[] args) {
        try {
            Config config = loadProperties(args);
            ControllerMapper controllerMapper = loadRequestControllersToControllerMapper();
            TemplateResolver templateResolver = new FileTemplateResolver(config.getTemplatePrefix(),
                    config.getTemplateSuffix());
            MethodHandler methodHandler = loadMethodHandler();
            HandlerManager handlerManager = loadSocketHandlerManager(controllerMapper, templateResolver,
                    config.getHttpVersion(), methodHandler);
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

    public abstract ControllerMapper loadRequestControllersToControllerMapper();

    public abstract MethodHandler loadMethodHandler();

    public abstract HandlerManager loadSocketHandlerManager(ControllerMapper controllerMapper,
                                                            TemplateResolver templateResolver,
                                                            String httpVersion,
                                                            MethodHandler methodHandler);

    public abstract Server loadServer(int port, HandlerManager handlerManager);
}
