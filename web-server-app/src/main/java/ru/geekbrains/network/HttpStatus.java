package ru.geekbrains.network;

public enum HttpStatus {

    OK (200, "OK"),
    NOT_FOUND (404, "NOT_FOUND");

    private int code;

    private String title;

    HttpStatus(int code, String title) {
    }

    @Override
    public String toString() {
        return code + " " + title;
    }
}
