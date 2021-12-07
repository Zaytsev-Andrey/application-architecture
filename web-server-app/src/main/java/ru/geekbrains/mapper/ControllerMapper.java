package ru.geekbrains.mapper;

import ru.geekbrains.controller.RequestController;

public interface ControllerMapper {

    RequestController mapControllerToRequest(String path);

    void addRequestController(String path, RequestController controller);
}
