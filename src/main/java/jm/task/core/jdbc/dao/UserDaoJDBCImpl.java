package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String DB_NAME = "users";

    private static final String CREATE_DB = String.format ("CREATE TABLE IF NOT EXISTS %s(" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "lastName VARCHAR(255)," +
                    "age TINYINT);", DB_NAME);
    private static final String DROP_DATABASE = String.format ("DROP TABLE IF EXISTS %s", DB_NAME);
    private static final String INSERT_DATA = String.format ("INSERT INTO %s (name, lastName, age) VALUES (?, ?, ?)", DB_NAME);
    private static final String DELETE_DATA = String.format ("DELETE FROM %s WHERE id = ?", DB_NAME);
    private static final String SELECT_ALL = String.format ("SELECT * FROM %s", DB_NAME);
    private static final String CLEAN_DATA = String.format ("DELETE FROM %s", DB_NAME);

    public UserDaoJDBCImpl() {

    }

    public void createTable() {

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(CREATE_DB);

        } catch (SQLException e) {
            System.out.println("Не удалось создать БД");
            throw new RuntimeException(e);
        }
    }

    public void dropTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(DROP_DATABASE);

        } catch (SQLException e) {
            System.out.println("Не удалось удалить БД");
            throw new RuntimeException(e);
        }
    }

    public void save(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DATA)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Не удалось сохранить нового User в БД");
            throw new RuntimeException(e);
        }
    }

    public void removeById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DATA)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Не удалось удалить User из БД");
            throw new RuntimeException(e);
        }
    }

    public List<User> getAll() {

        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {

                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);

                users.add(user);
            }
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("Не удалось получить всех User из БД");
            throw new RuntimeException(e);
        }

        return users;
    }

    public void cleanTable() {

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(CLEAN_DATA);

        } catch (SQLException e) {
            System.out.println("Не удалось очистить БД");
            throw new RuntimeException(e);
        }

    }
}
