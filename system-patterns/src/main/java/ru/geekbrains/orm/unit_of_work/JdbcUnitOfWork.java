package ru.geekbrains.orm.unit_of_work;

import ru.geekbrains.orm.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class JdbcUnitOfWork implements UnitOfWork {

    private final Connection connection;

    private final List<User> newUsers = new ArrayList<>();

    private final List<User> updateUsers = new ArrayList<>();

    private final List<User> deleteUsers = new ArrayList<>();

    public JdbcUnitOfWork(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void registerNew(User user) {
        this.newUsers.add(user);
    }

    @Override
    public void registerUpdate(User user) {
        this.updateUsers.add(user);
    }

    @Override
    public void registerDelete(User user) {
        this.deleteUsers.add(user);
    }

    @Override
    public void commit() {
        insert();
        update();
        delete();
    }

    private void insert() {
        try {
            PreparedStatement insertUser = connection.prepareStatement("insert into users values ( ?, ?, ? );");
            for (User user : newUsers) {
                insertUser.setLong(1, user.getId());
                insertUser.setString(2, user.getLogin());
                insertUser.setString(3, user.getPassword());
                insertUser.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void update() {
        try {
            PreparedStatement insertUser = connection
                    .prepareStatement("update users set login = ?, password = ? where id = ?");
            for (User user : updateUsers) {
                insertUser.setString(1, user.getLogin());
                insertUser.setString(2, user.getPassword());
                insertUser.setLong(3, user.getId());
                insertUser.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void delete() {
        try {
            PreparedStatement insertUser = connection
                    .prepareStatement("delete from users where id = ?");
            for (User user : deleteUsers) {
                insertUser.setLong(1, user.getId());
                insertUser.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
