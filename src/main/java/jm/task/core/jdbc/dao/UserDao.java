package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    void createTable();

    void dropTable();

    void save(String name, String lastName, byte age);

    void removeById(long id);

    List<User> getAll();

    void cleanTable();
}
