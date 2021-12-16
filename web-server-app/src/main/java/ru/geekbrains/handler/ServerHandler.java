package ru.geekbrains.handler;

import ru.geekbrains.controller.RequestController;
import ru.geekbrains.mapper.ControllerMapper;
import ru.geekbrains.network.HttpStatus;
import ru.geekbrains.network.NetworkService;
import ru.geekbrains.network.request.HttpRequest;
import ru.geekbrains.resolver.TemplateResolver;

import java.nio.file.Path;

class ServerHandler implements Runnable {

    private NetworkService networkService;

    private ControllerMapper controllerMapper;

    private TemplateResolver templateResolver;

    public ServerHandler(NetworkService networkService, ControllerMapper controllerMapper, TemplateResolver templateResolver) {
        this.networkService = networkService;
        this.controllerMapper = controllerMapper;
        this.templateResolver = templateResolver;
    }

    @Override
    public void run() {
        HttpRequest httpRequest = networkService.readRequest();
        RequestController controller = controllerMapper.mapControllerToRequest(httpRequest.getUrl());

        String template;
        Path templatePath;
        HttpStatus httpStatus;
        if (controller != null) {
            template = controller.handleRequest();
            templatePath = templateResolver.resolveTemplatePathByName(template);
            httpStatus = HttpStatus.OK;
        } else {
            templatePath = templateResolver.resolveTemplatePathByName("not_found");
            httpStatus = HttpStatus.NOT_FOUND;
        }

        networkService.writeResponse(httpRequest.getSession(), templatePath, httpStatus);

        networkService.close();
    }

}