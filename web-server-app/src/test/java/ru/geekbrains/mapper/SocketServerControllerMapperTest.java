package ru.geekbrains.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controller.ContactPageRequestController;
import ru.geekbrains.controller.HomePageRequestController;
import ru.geekbrains.model.Model;
import ru.geekbrains.model.ModelFactory;

public class SocketServerControllerMapperTest {

    @Test
    public void controllerMapperTest() {
        ControllerMapper controllerMapper = new SocketServerControllerMapper();
        controllerMapper.addRequestController("/", new HomePageRequestController());
        controllerMapper.addRequestController("/contact", new ContactPageRequestController());
        Model model = ModelFactory.createModel();

        Assertions.assertEquals("home", controllerMapper.mapControllerToRequest("/").doGet(model));
        Assertions.assertEquals("contact", controllerMapper.mapControllerToRequest("/contact").doGet(model));
        Assertions.assertNull(controllerMapper.mapControllerToRequest("/cart"));
    }
}
