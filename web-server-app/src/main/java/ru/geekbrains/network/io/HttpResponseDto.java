package ru.geekbrains.network.io;

import ru.geekbrains.network.HttpStatus;

public class HttpResponseDto {

    private HttpStatus status;

    private String templateName;

    public HttpResponseDto(HttpStatus status, String templateName) {
        this.status = status;
        this.templateName = templateName;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getTemplateName() {
        return templateName;
    }
}
