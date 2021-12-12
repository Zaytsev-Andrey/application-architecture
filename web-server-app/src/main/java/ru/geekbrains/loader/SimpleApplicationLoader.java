package ru.geekbrains.loader;

import ru.geekbrains.controller.HomePageRequestController;
import ru.geekbrains.handler.HttpHandlerManager;
import ru.geekbrains.handler.HandlerManager;
import ru.geekbrains.mapper.ControllerMapper;
import ru.geekbrains.mapper.SocketServerControllerMapper;
import ru.geekbrains.network.HttpRequestManager;
import ru.geekbrains.network.HttpResponseManager;
import ru.geekbrains.network.RequestManager;
import ru.geekbrains.network.ResponseManager;
import ru.geekbrains.network.session.SessionRepository;
import ru.geekbrains.network.session.SimpleSessionRepository;
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
    public HandlerManager loadSocketHandlerManager(ControllerMapper controllerMapper,
                                                   TemplateResolver templateResolver) {
        SessionRepository sessionRepository = new SimpleSessionRepository();
        RequestManager requestManager = new HttpRequestManager(sessionRepository);
        ResponseManager responseManager = new HttpResponseManager();
        return new HttpHandlerManager(controllerMapper, templateResolver, requestManager, responseManager);
    }

    @Override
    public Server loadServer(HandlerManager handlerManager) {
        return new SocketServer(handlerManager);
    }
}
