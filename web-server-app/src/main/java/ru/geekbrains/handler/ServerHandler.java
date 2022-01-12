package ru.geekbrains.handler;

import ru.geekbrains.controller.RequestController;
import ru.geekbrains.handler.method_handler.MethodHandler;
import ru.geekbrains.mapper.ControllerMapper;
import ru.geekbrains.model.Model;
import ru.geekbrains.network.HttpStatus;
import ru.geekbrains.network.NetworkService;
import ru.geekbrains.network.io.HttpResponseDto;
import ru.geekbrains.network.request.HttpRequest;
import ru.geekbrains.resolver.TemplateResolver;
import ru.geekbrains.view.TemplateEngine;

import java.nio.file.Path;

class ServerHandler implements Runnable {

    private NetworkService networkService;

    private ControllerMapper controllerMapper;

    private TemplateResolver templateResolver;

    private MethodHandler methodHandler;

    private Model model;

    private TemplateEngine templateEngine;

    public ServerHandler(NetworkService networkService,
                         ControllerMapper controllerMapper,
                         TemplateResolver templateResolver,
                         MethodHandler methodHandler,
                         Model model,
                         TemplateEngine templateEngine) {
        this.networkService = networkService;
        this.controllerMapper = controllerMapper;
        this.templateResolver = templateResolver;
        this.methodHandler = methodHandler;
        this.model = model;
        this.templateEngine = templateEngine;
    }

    @Override
    public void run() {
        HttpRequest httpRequest = networkService.readRequest();
        RequestController controller = controllerMapper.mapControllerToRequest(httpRequest.getUrl());

        HttpResponseDto responseDto;
        if (controller != null) {
            responseDto = methodHandler.doMethod(controller, httpRequest.getMethod(), model);
        } else {
            responseDto = new HttpResponseDto(HttpStatus.NOT_FOUND, "not_found");
        }

        Path templatePath = templateResolver.resolveTemplatePathByName(responseDto.getTemplateName());

        String view = templateEngine.getView(templatePath);

        networkService.writeResponse(httpRequest.getSession(), view, responseDto.getStatus());

        networkService.close();
    }

}