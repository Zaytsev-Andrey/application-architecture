package ru.geekbrains.mapper;

import ru.geekbrains.controller.RequestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Реализация интерфейса ControllerMapper хранящая ссылки на контроллеры в HashMap, в качестве ключа выступает параметр
 * запроса
 */
public class SocketServerControllerMapper implements ControllerMapper {

    private Map<String, RequestController> controllers = new HashMap<>();

    @Override
    public RequestController mapControllerToRequest(String path) {
        return controllers.get(path);
    }

    @Override
    public void addRequestController(String path, RequestController controller) {
        controllers.put(path, controller);
    }
}
