package ru.geekbrains.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {

    private static final String url = "jdbc:mysql://localhost:3306/system_patterns";

    private static final String user = "student";

    private static final String password = "Pas123456";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            User user = new User(1, "admin", "Pa$$word");
            User guest = new User(2, "guest", "Pa$$word");

            UserRepository repository = new UserRepository(connection);
            repository.beginTransactional();
            repository.insert(user);
            repository.insert(guest);
            user.setPassword("12345");
            repository.update(user);
            repository.delete(guest);
            repository.commit();

            System.out.println(repository.findById(1));

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
