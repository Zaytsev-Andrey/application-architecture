package ru.geekbrains.loader;

import ru.geekbrains.controller.RequestController;
import ru.geekbrains.handler.method_handler.Handler;
import ru.geekbrains.handler.method_handler.MethodHandler;
import ru.geekbrains.mapper.ControllerMapper;
import ru.geekbrains.mapper.RequestMappingController;
import ru.geekbrains.mapper.SocketServerControllerMapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс расширяющий SimpleApplicationLoader с переопределенным методом
 * загрузки контроллеров реализующих интерфейс RequestController используя сканирование классов в пакете controller
 * помеченных анотацией RequestMappingController значение параметра которой является пареметром обрабатываемого запроса.
 */
public class ApplicationLoaderWithScanning extends SimpleApplicationLoader {

    @Override
    public ControllerMapper loadRequestControllersToControllerMapper() {
        ControllerMapper controllerMapper = new SocketServerControllerMapper();

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String packagePath = "ru/geekbrains/controller";
            URL resource = classLoader.getResource(packagePath);

            Path rootPackage = Path.of(resource.getFile());
            Files.walkFileTree(rootPackage, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().toLowerCase().endsWith(".class")) {
                        try {
                            String packageName = packagePath
                                    .replaceAll("/", ".").concat(".");
                            String className = file.getFileName().toString();
                            className = className.substring(0, className.lastIndexOf("."));
                            Class controllerClass = classLoader
                                    .loadClass(packageName + className);

                            for (Class i : controllerClass.getInterfaces()) {
                                if (i.equals(RequestController.class)) {
                                    RequestMappingController annotation = (RequestMappingController) (controllerClass
                                        .getAnnotation(RequestMappingController.class));
                                    controllerMapper.addRequestController(annotation.path(),
                                            (RequestController) controllerClass.newInstance());
                                }
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    return super.visitFile(file, attrs);
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return controllerMapper;
    }

    @Override
    public MethodHandler loadMethodHandler() {
        Deque<MethodHandler> handlers = new LinkedList<>();

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String packagePath = "ru/geekbrains/handler/method_handler";
            URL resource = classLoader.getResource(packagePath);

            Path rootPackage = Path.of(resource.getFile());
            Files.walkFileTree(rootPackage, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().toLowerCase().endsWith(".class")) {
                        try {
                            String packageName = packagePath
                                    .replaceAll("/", ".").concat(".");
                            String className = file.getFileName().toString();
                            className = className.substring(0, className.lastIndexOf("."));
                            Class handlerClass = classLoader
                                    .loadClass(packageName + className);

                            Handler annotation = (Handler) (handlerClass.getAnnotation(Handler.class));

                            if (annotation != null) {
                                handlers.push((MethodHandler) handlerClass
                                        .getConstructor(String.class)
                                        .newInstance(annotation.method()));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    return super.visitFile(file, attrs);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (!handlers.isEmpty()) {
            MethodHandler currentHandler = handlers.pop();
            MethodHandler nextHandler;
            while (!handlers.isEmpty()) {
                nextHandler = handlers.pop();
                currentHandler.setNext(nextHandler);
                currentHandler = nextHandler;
            }
        }

        return super.loadMethodHandler();
    }
}
