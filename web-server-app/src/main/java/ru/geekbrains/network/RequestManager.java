package ru.geekbrains.network;

import ru.geekbrains.network.io.HttpRequestDto;
import ru.geekbrains.network.request.HttpRequest;

public interface RequestManager {

    HttpRequest newHttpRequest(HttpRequestDto requestDto);
}
