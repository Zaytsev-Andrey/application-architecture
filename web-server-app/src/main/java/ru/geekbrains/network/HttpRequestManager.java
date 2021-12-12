package ru.geekbrains.network;

import ru.geekbrains.network.io.HttpRequestDto;
import ru.geekbrains.network.request.*;
import ru.geekbrains.network.session.Principal;
import ru.geekbrains.network.session.Session;
import ru.geekbrains.network.session.SessionRepository;

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
                session = newSession();
            }
        } else {
            session = newSession();
        }

        HttpRequest request = new HttpRequest.HttpRequestBuilder()
                .method(requestDto.getMethod())
                .url(requestDto.getUrl())
                .headers(new HttpHeaders(requestDto.getHeaders()))
                .cookies(new HttpCookies(requestDto.getCookies()))
                .body(requestDto.getBody())
                .session(session)
                .build();

        return request;
    }

    private Session newSession() {
        Session session = new Session(new Principal("Friend", ""));
        sessionRepository.save(session);
        String sessionId = session.getId().toString();
        HttpCookies cookies = session.getNewCookies();
        cookies.add("SESSIONID", sessionId);
        return session;
    }
}
