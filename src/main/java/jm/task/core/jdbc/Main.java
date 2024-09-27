package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Иван", "Иванович", (byte) 23);
        userService.saveUser("Петр", "Петрович", (byte) 58);
        userService.saveUser("Семен", "Семенович", (byte) 39);
        userService.saveUser("Николай", "Николаевич", (byte) 19);

        List<User> users = userService.getAllUsers();

        for (User u : users) {
            System.out.println (u);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
