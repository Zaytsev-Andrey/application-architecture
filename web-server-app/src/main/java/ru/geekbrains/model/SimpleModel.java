package ru.geekbrains.model;

import java.util.HashMap;
import java.util.Map;

class SimpleModel implements Model {

    private final Map<String, String> model = new HashMap<>();

    @Override
    public void addAttribute(String name, String value) {
        model.put(name, value);
    }

    @Override
    public String getAttribute(String name) {
        return model.getOrDefault(name, "");
    }
}
