package ru.geekbrains.handler;

import ru.geekbrains.controller.RequestController;
import ru.geekbrains.mapper.ControllerMapper;
import ru.geekbrains.resolver.TemplateResolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


/**
 * Реализация интерфейса SocketHandlerManager отвечает за создание новых обектов ServerHandler во время работы программы
 */
public class SimpleSocketHandlerManager implements SocketHandlerManager {

    private ControllerMapper controllerMapper;

    private TemplateResolver templateResolver;

    public SimpleSocketHandlerManager(ControllerMapper controllerMapper, TemplateResolver templateResolver) {
        this.controllerMapper = controllerMapper;
        this.templateResolver = templateResolver;
    }

    @Override
    public Runnable getSocketHandler(Socket socket) {
        return new ServerHandler(socket, controllerMapper, templateResolver);
    }

    private class ServerHandler implements Runnable {

        private Socket socket;

        private ControllerMapper controllerMapper;

        private TemplateResolver templateResolver;

        public ServerHandler(Socket socket, ControllerMapper controllerMapper, TemplateResolver templateResolver) {
            this.socket = socket;
            this.controllerMapper = controllerMapper;
            this.templateResolver = templateResolver;
        }

        @Override
        public void run() {
            try (BufferedReader input = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream(), StandardCharsets.UTF_8));
                 PrintWriter output = new PrintWriter(socket.getOutputStream())
            ) {

                String[] stringRequest = readStringRequest(input);

                RequestController controller = controllerMapper.mapControllerToRequest(stringRequest[1]);

                System.out.println("String request " + Arrays.toString(stringRequest));
                System.out.println("Template path " + stringRequest[1]);

                String templateName = controller.handleRequest();
                Path templatePath = templateResolver.resolveTemplatePathByName(templateName);
                sendResponse(templatePath.toAbsolutePath(), output);
//                Path path = Paths.get(WWW, parts[1]);
                System.out.println("Client disconnected!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String[] readStringRequest(BufferedReader input) throws IOException {
            while (!input.ready());

            String firstLine = input.readLine();
            String[] parts = firstLine.split(" ");
//            System.out.println(firstLine);
            while (input.ready()) {
                input.readLine();
//                System.out.println(input.readLine());
            }

            return parts;
        }

        private void sendResponse(Path path, PrintWriter output) throws IOException {
            if (!Files.exists(path)) {
                output.println("HTTP/1.1 404 NOT_FOUND");
                output.println("Content-Type: text/html; charset=utf-8");
                output.println();
                output.println("<h1>Файл не найден!</h1>");
                output.flush();
                return;
            }

            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();

            Files.newBufferedReader(path).transferTo(output);
        }
    }

}
