package ru.geekbrains.loader;

import ru.geekbrains.controller.HomePageRequestController;
import ru.geekbrains.handler.SimpleSocketHandlerManager;
import ru.geekbrains.handler.SocketHandlerManager;
import ru.geekbrains.mapper.ControllerMapper;
import ru.geekbrains.mapper.SocketServerControllerMapper;
import ru.geekbrains.resolver.TemplateResolver;
import ru.geekbrains.server.Server;
import ru.geekbrains.server.SocketServer;

/**
 * Простая реализация шаблонного класса выполняющая создание экземпляров текущих классов и установки зависимостей между
 * ними
 */
public class SimpleApplicationLoader extends AbstractApplicationLoader {
    @Override
    public ControllerMapper loadRequestControllersToControllerMapper() {
        ControllerMapper controllerMapper = new SocketServerControllerMapper();
        controllerMapper.addRequestController("/", new HomePageRequestController());

        return controllerMapper;
    }

    @Override
    public SocketHandlerManager loadSocketHandlerManager(ControllerMapper controllerMapper,
                                                         TemplateResolver templateResolver) {
        return new SimpleSocketHandlerManager(controllerMapper, templateResolver);
    }

    @Override
    public Server loadServer(SocketHandlerManager handlerManager) {
        return new SocketServer(handlerManager);
    }
}
