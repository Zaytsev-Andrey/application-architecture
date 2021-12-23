package ru.geekbrains.handler.method_handler;

import ru.geekbrains.controller.RequestController;
import ru.geekbrains.network.HttpStatus;
import ru.geekbrains.network.io.HttpResponseDto;

@Handler
public class PostMethodHandler extends MethodHandler {

    public PostMethodHandler(String method) {
        super(method);
    }

    @Override
    protected HttpResponseDto doMethod(RequestController controller) {
        return new HttpResponseDto(HttpStatus.OK, "home");
    }
}
