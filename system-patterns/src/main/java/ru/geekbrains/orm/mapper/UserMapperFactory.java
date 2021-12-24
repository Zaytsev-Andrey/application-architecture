package ru.geekbrains.orm.mapper;

import java.sql.Connection;

public class UserMapperFactory {

    public static UserMapper createUserMapper(Connection connection) {
        return new JdbcUserMapper(connection);
    }
}
