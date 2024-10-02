package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

//public class UserServiceImpl implements UserService {
//
//    private UserDaoHibernateImpl UserDaoHibernate;
//
//    public UserServiceImpl () {
//        this.UserDaoHibernate = new UserDaoHibernateImpl();
//    }
//
//    public void createTable() {
//        UserDaoHibernate.createTable();
//    }
//
//    public void dropTable() {
//        UserDaoHibernate.dropTable();
//    }
//
//    public void save(String name, String lastName, byte age) {
//        UserDaoHibernate.save(name, lastName, age);
//        System.out.println("User с именем " + name + " добавлен в базу данных");
//    }
//
//    public void removeById(long id) {
//        UserDaoHibernate.removeById(id);
//    }
//
//    public List<User> getAll() {
//        return UserDaoHibernate.getAll();
//    }
//
//    public void cleanTable() {
//        UserDaoHibernate.cleanTable();
//    }
//}

//===========================================================
// реализация для JDBC
//===========================================================
public class UserServiceImpl implements UserService {

    private UserDaoJDBCImpl userDaoJDBC;

    public UserServiceImpl () {
        this.userDaoJDBC = new UserDaoJDBCImpl();
    }

    public void createTable() {
        userDaoJDBC.createTable();
    }

    public void dropTable() {
        userDaoJDBC.dropTable();
    }

    public void save(String name, String lastName, byte age) {
        userDaoJDBC.save(name, lastName, age);
        System.out.println("User с именем " + name + " добавлен в базу данных");
    }

    public void removeById(long id) {
        userDaoJDBC.removeById(id);
    }

    public List<User> getAll() {
        return userDaoJDBC.getAll();
    }

    public void cleanTable() {
        userDaoJDBC.cleanTable();
    }
}