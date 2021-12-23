package ru.geekbrains.handler.method_handler;

import ru.geekbrains.controller.RequestController;
import ru.geekbrains.network.HttpStatus;
import ru.geekbrains.network.io.HttpResponseDto;

@Handler
public class GetMethodHandler extends MethodHandler {

    public GetMethodHandler(String method) {
        super(method);
    }

    @Override
    protected HttpResponseDto doMethod(RequestController controller) {
        return new HttpResponseDto(HttpStatus.OK, controller.doGet());
    }
}
