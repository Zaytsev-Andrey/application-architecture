package ru.geekbrains.network.session;

public class Principal {

    private int counter = 1;

    private String name;

    private String password;

    public Principal(String name, String password) {
        this.name = name + "-" + counter++;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
