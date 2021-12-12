package ru.geekbrains.network.response;

import java.util.ArrayList;
import java.util.List;

public class HttpContentType {

    private List<String> contentTypes = new ArrayList<>();

    public HttpContentType() {
        contentTypes.add("text/html");
        contentTypes.add("charset=utf-8");
    }

    @Override
    public String toString() {
        return String.join("; ", contentTypes);
    }
}
