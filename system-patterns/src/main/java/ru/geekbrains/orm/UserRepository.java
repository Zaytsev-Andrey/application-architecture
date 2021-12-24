package ru.geekbrains.orm;

import ru.geekbrains.orm.mapper.UserMapper;
import ru.geekbrains.orm.mapper.UserMapperFactory;
import ru.geekbrains.orm.unit_of_work.UnitOfWork;
import ru.geekbrains.orm.unit_of_work.UnitOfWorkFactory;

import java.sql.Connection;
import java.util.Optional;

public class UserRepository {

    private final Connection connection;

    private final UserMapper userMapper;

    private UnitOfWork unitOfWork;

    public UserRepository(Connection connection) {
        this.connection = connection;
        this.userMapper = UserMapperFactory.createUserMapper(connection);
        this.unitOfWork = UnitOfWorkFactory.createUnitOfWork(connection);
    }

    public Optional<User> findById(long id) {
        return userMapper.findById(id);
    }

    public void beginTransactional() {
        this.unitOfWork = UnitOfWorkFactory.createUnitOfWork(connection);
    }

    public void insert(User user) {
        unitOfWork.registerNew(user);
    }

    public void update(User user) {
        unitOfWork.registerUpdate(user);
    }

    public void delete(User user) {
        unitOfWork.registerDelete(user);
    }

    public void commit() {
        unitOfWork.commit();
    }
}
