package ru.geekbrains.handler.method_handler;

import ru.geekbrains.controller.RequestController;
import ru.geekbrains.network.HttpStatus;
import ru.geekbrains.network.io.HttpResponseDto;

public abstract class MethodHandler {

    private final String method;

    private MethodHandler next;

    public MethodHandler(String method) {
        this.method = method;
    }

    public void setNext(MethodHandler next) {
        this.next = next;
    }

    public HttpResponseDto doMethod(RequestController controller, String method) {
        if (this.method.equals(method)) {
            return doMethod(controller);
        } else if (next != null) {
            return next.doMethod(controller, method);
        } else {
            return new HttpResponseDto(HttpStatus.METHOD_NOT_ALLOWED, "not_allowed");
        }
    }

    protected abstract HttpResponseDto doMethod(RequestController controller);
}
