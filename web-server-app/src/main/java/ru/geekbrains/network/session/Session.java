package ru.geekbrains.network.session;

import ru.geekbrains.network.request.HttpCookies;

import java.util.UUID;

/**
 * Класс для хранения HTTP сессии
 */
public class Session {

    private UUID id;

    private Principal principal;

    private HttpCookies newCookies;

    public Session(Principal principal) {
        this.id = UUID.randomUUID();
        this.principal = principal;
        this.newCookies = new HttpCookies();
    }

    public UUID getId() {
        return id;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public HttpCookies getNewCookies() {
        return newCookies;
    }
}
