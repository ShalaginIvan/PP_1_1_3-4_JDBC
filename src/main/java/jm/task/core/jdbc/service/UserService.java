package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserService {
    void createTable();

    void dropTable();

    void save(String name, String lastName, byte age);

    void removeById(long id);

    List<User> getAll();

    void cleanTable();
}
