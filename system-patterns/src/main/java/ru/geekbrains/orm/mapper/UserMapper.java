package ru.geekbrains.orm.mapper;

import ru.geekbrains.orm.User;

import java.util.Optional;

public interface UserMapper {

    Optional<User> findById(long id);
}
