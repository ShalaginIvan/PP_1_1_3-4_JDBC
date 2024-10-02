package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL_DB = "jdbc:mysql://localhost:3306/dbworktest";
    //private static final String URL_DB = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER_JDBC = "com.mysql.cj.jdbc.Driver";
    private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static SessionFactory sessionFactory;

    public static Connection getConnection() {

        Connection connection = null;
        try {
            Class.forName(DRIVER_JDBC);
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось зарегистрировать драйвер");
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL_DB, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Не удалось установить соединение с БД");
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DRIVER_JDBC);
                settings.put(Environment.URL, URL_DB);
                settings.put(Environment.USER, USER_NAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, HIBERNATE_DIALECT);

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "none");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.out.println("Не удалось установить соединение с БД");
                throw new RuntimeException(e);
            }
        }

        return sessionFactory;
    }





}
