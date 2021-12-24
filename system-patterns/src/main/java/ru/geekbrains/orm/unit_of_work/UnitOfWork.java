package ru.geekbrains.orm.unit_of_work;

import ru.geekbrains.orm.User;

public interface UnitOfWork {

    void registerNew(User user);

    void registerUpdate(User user);

    void registerDelete(User user);

    void commit();
}
