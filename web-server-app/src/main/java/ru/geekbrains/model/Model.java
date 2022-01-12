package ru.geekbrains.model;

public interface Model {

    void addAttribute(String name, String value);

    String getAttribute(String name);
}
