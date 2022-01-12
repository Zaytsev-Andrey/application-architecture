package ru.geekbrains.loader;

import org.reflections.Reflections;
import ru.geekbrains.controller.RequestController;
import ru.geekbrains.handler.method_handler.Handler;
import ru.geekbrains.handler.method_handler.MethodHandler;
import ru.geekbrains.mapper.ControllerMapper;
import ru.geekbrains.mapper.RequestMappingController;
import ru.geekbrains.mapper.SocketServerControllerMapper;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс расширяющий SimpleApplicationLoader с переопределенным методом
 * загрузки контроллеров реализующих интерфейс RequestController используя сканирование классов в пакете controller
 * помеченных анотацией RequestMappingController значение параметра которой является пареметром обрабатываемого запроса.
 */

// В классе был обнаружен методологический антипаттерн "Изобретение велосипеда", в виду того, что была написана
// собственная реализация сканирования пакетов в поисках классов помеченных определенными аннотациями вместо
// использования готовой библиотеки.
// В следствии вышесказанного в методах можно заметить еще один атипаттерн "Спагетти-код". Один метод выполняет
// сканирование пакета в поиске классов помеченных аннотацией, извлечением параметров аннотации и создания
// на их основании объектов. Все это приводит к объемному и неструктурированному коду метода.
// Пакеты для сканирования определены в виде строковых литералов в коде, что является антипаттерном "Магические числа".

// Для устранения антипаттернов "Изобретение велосипеда" и "Спагетти-код" была использована сторонняя библиотека
// "reflections" для сканирования пакетов в поисках классов помеченных определенными аннотациями. Это гарантировало
// надежный и проверенный функционал сканировани и в тоже время уменьшило обоем и структурировало код методов.
// Для устранения антипаттерна "Магически числа" строковые литералы были вынесены в properties файл.
public class ApplicationLoaderWithScanning extends SimpleApplicationLoader {

    @Override
    public ControllerMapper loadRequestControllersToControllerMapper(String requestControllerPackage) {
        ControllerMapper controllerMapper = new SocketServerControllerMapper();
        Reflections reflections = new Reflections(requestControllerPackage);
        List<Class<?>> requestControllerClasses =
                new ArrayList<>(reflections.getTypesAnnotatedWith(RequestMappingController.class));

        try {
            for (Class<?> clazz : requestControllerClasses) {
                Constructor<?> constructor = clazz.getConstructor();
                controllerMapper.addRequestController(
                        clazz.getAnnotation(RequestMappingController.class).path(),
                        (RequestController) constructor.newInstance()
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return controllerMapper;
    }

    @Override
    public MethodHandler loadMethodHandler(String methodHandlerPackage) {
        Reflections reflections = new Reflections(methodHandlerPackage);
        List<Class<?>> methodHandlerClasses =
                new ArrayList<>(reflections.getTypesAnnotatedWith(Handler.class));

        MethodHandler methodHandler = null;
        try {
            for (Class<?> clazz : methodHandlerClasses) {
                Constructor<?> constructor = clazz.getConstructor(String.class);
                MethodHandler currentMethodHandler = (MethodHandler) constructor.newInstance(
                        clazz.getAnnotation(Handler.class).method()
                );
                currentMethodHandler.setNext(methodHandler);
                methodHandler = currentMethodHandler;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return methodHandler;
    }
}
