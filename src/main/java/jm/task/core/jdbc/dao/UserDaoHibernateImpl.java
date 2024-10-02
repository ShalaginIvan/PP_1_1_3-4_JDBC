package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {

    private static final String DB_NAME = "users";
    private static final String CREATE_DB = String.format ("CREATE TABLE IF NOT EXISTS %s(" +
            "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
            "name VARCHAR(255)," +
            "lastName VARCHAR(255)," +
            "age TINYINT);", DB_NAME);

    private static final String DROP_DATABASE = String.format ("DROP TABLE IF EXISTS %s", DB_NAME);
    public UserDaoHibernateImpl() {

    }

    private void SQLQuery (String sql) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void createTable() {
        SQLQuery(CREATE_DB);
    }

    @Override
    public void dropTable() {
        SQLQuery(DROP_DATABASE);
    }

    @Override
    public void save(String name, String lastName, byte age) {

        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(long id) {

        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            User user = session.get(User.class, id);
            session.delete(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<>();

        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            users = session.createQuery("from User").getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void cleanTable() {

        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            session.createQuery("delete User").executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
