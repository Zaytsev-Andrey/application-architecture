package ru.geekbrains.handler;

import ru.geekbrains.controller.RequestController;
import ru.geekbrains.handler.method_handler.MethodHandler;
import ru.geekbrains.mapper.ControllerMapper;
import ru.geekbrains.network.HttpStatus;
import ru.geekbrains.network.NetworkService;
import ru.geekbrains.network.io.HttpResponseDto;
import ru.geekbrains.network.request.HttpRequest;
import ru.geekbrains.resolver.TemplateResolver;

import java.nio.file.Path;

class ServerHandler implements Runnable {

    private NetworkService networkService;

    private ControllerMapper controllerMapper;

    private TemplateResolver templateResolver;

    private MethodHandler methodHandler;

    public ServerHandler(NetworkService networkService,
                         ControllerMapper controllerMapper,
                         TemplateResolver templateResolver,
                         MethodHandler methodHandler) {
        this.networkService = networkService;
        this.controllerMapper = controllerMapper;
        this.templateResolver = templateResolver;
        this.methodHandler = methodHandler;
    }

    @Override
    public void run() {
        HttpRequest httpRequest = networkService.readRequest();
        RequestController controller = controllerMapper.mapControllerToRequest(httpRequest.getUrl());

//        String template;
//        Path templatePath;
//        HttpStatus httpStatus;
//        if (controller != null) {
//            template = controller.doGet();
//            templatePath = templateResolver.resolveTemplatePathByName(template);
//            httpStatus = HttpStatus.OK;
//        } else {
//            templatePath = templateResolver.resolveTemplatePathByName("not_found");
//            httpStatus = HttpStatus.NOT_FOUND;
//        }

        HttpResponseDto responseDto;
        if (controller != null) {
            responseDto = methodHandler.doMethod(controller, httpRequest.getMethod());
        } else {
            responseDto = new HttpResponseDto(HttpStatus.NOT_FOUND, "not_found");
        }

        Path templatePath = templateResolver.resolveTemplatePathByName(responseDto.getTemplateName());

        networkService.writeResponse(httpRequest.getSession(), templatePath, responseDto.getStatus());

        networkService.close();
    }

}