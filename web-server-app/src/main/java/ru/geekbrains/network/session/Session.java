package ru.geekbrains.network.session;

import ru.geekbrains.network.request.HttpCookies;

import java.util.UUID;

public interface Session {

    UUID getId();

    Principal getPrincipal();

    HttpCookies getNewCookies();
}
