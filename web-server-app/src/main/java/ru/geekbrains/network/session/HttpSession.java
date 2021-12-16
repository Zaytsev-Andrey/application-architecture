package ru.geekbrains.network.session;

import ru.geekbrains.network.request.HttpCookies;

import java.util.UUID;

/**
 * Класс для хранения HTTP сессии
 */
class HttpSession implements Session {

    private UUID id;

    private Principal principal;

    private HttpCookies newCookies;

    public HttpSession(Principal principal) {
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
