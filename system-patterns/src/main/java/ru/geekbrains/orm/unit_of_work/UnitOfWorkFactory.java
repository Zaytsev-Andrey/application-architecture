package ru.geekbrains.orm.unit_of_work;


import java.sql.Connection;

public class UnitOfWorkFactory {

    public static UnitOfWork createUnitOfWork(Connection connection) {
        return new JdbcUnitOfWork(connection);
    }
}
