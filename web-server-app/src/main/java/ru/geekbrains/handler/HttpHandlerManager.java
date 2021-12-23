package ru.geekbrains.handler;

import ru.geekbrains.handler.method_handler.MethodHandler;
import ru.geekbrains.mapper.ControllerMapper;
import ru.geekbrains.network.*;
import ru.geekbrains.resolver.TemplateResolver;

import java.net.Socket;

public class HttpHandlerManager implements HandlerManager {

    private ControllerMapper controllerMapper;

    private TemplateResolver templateResolver;

    private RequestManager requestManager;

    private ResponseManager responseManager;

    private MethodHandler methodHandler;

    public HttpHandlerManager(ControllerMapper controllerMapper,
                              TemplateResolver templateResolver,
                              RequestManager requestManager,
                              ResponseManager responseManager,
                              MethodHandler methodHandler) {
        this.controllerMapper = controllerMapper;
        this.templateResolver = templateResolver;
        this.requestManager = requestManager;
        this.responseManager = responseManager;
        this.methodHandler = methodHandler;
    }

    @Override
    public Runnable newSocketHandler(Socket socket) {
        NetworkService networkService = new HttpNetworkService(socket, requestManager, responseManager);

        return new ServerHandler(networkService, controllerMapper, templateResolver, methodHandler);
    }
}
