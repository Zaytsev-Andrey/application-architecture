package ru.geekbrains.orm;

public class User {

    private long id;

    private String login;

    private String password;

    public User(long id, String email, String password) {
        this.id = id;
        this.login = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("id='%d', login='%s', password='%s'", this.id, this.login, this.password);
    }
}
