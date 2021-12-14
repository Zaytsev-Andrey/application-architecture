package ru.geekbrains.network;

import ru.geekbrains.network.io.HttpRequestDto;
import ru.geekbrains.network.request.*;
import ru.geekbrains.network.session.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Фабричный класс для создания экземпляров класса HttpRequest. Класс имеет состояние sessionRepository,
 * поэтому фабричный метод не статический
 */
public class HttpRequestManager implements RequestManager {

    private SessionRepository sessionRepository;

    public HttpRequestManager(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public HttpRequest newHttpRequest(HttpRequestDto requestDto) {
        Session session;
        String sessionId = requestDto.getCookies().get("SESSIONID");

        if (sessionId != null) {
            UUID id = UUID.fromString(sessionId);
            Optional<Session> sessionOpt = sessionRepository.findById(id);

            if (sessionOpt.isPresent()) {
                session = sessionOpt.get();
            } else {
                session = sessionRepository.save(SessionFactory.create());
            }
        } else {
            session = sessionRepository.save(SessionFactory.create());
        }

        HttpRequest request = new HttpRequest.Builder()
                .withMethod(requestDto.getMethod())
                .withUrl(requestDto.getUrl())
                .withVersion(requestDto.getVersion())
                .withHeaders(new HttpHeaders(requestDto.getHeaders()))
                .withCookies(new HttpCookies(requestDto.getCookies()))
                .withBody(requestDto.getBody())
                .withSession(session)
                .build();

        return request;
    }

}
