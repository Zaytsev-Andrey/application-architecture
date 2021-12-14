package ru.geekbrains.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controller.ContactPageRequestController;
import ru.geekbrains.controller.HomePageRequestController;

public class SocketServerControllerMapperTest {

    @Test
    public void controllerMapperTest() {
        ControllerMapper controllerMapper = new SocketServerControllerMapper();
        controllerMapper.addRequestController("/", new HomePageRequestController());
        controllerMapper.addRequestController("/contact", new ContactPageRequestController());

        Assertions.assertEquals("home", controllerMapper.mapControllerToRequest("/").handleRequest());
        Assertions.assertEquals("contact", controllerMapper.mapControllerToRequest("/contact").handleRequest());
        Assertions.assertNull(controllerMapper.mapControllerToRequest("/cart"));
    }
}
