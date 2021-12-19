package ru.geekbrains.flyweight;

import java.util.HashMap;
import java.util.Map;

public class TeaMaker {

    private Map<String, KarakTea> availableTea = new HashMap<>();

    public KarakTea make(String preference) {
        availableTea.putIfAbsent(preference, new KarakTea());

        return availableTea.get(preference);
    }
}
