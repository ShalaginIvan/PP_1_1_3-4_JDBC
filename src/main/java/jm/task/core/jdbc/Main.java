package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createTable();

        userService.save("Иван", "Иванович", (byte) 23);
        userService.save("Петр", "Петрович", (byte) 58);
        userService.save("Семен", "Семенович", (byte) 39);
        userService.save("Николай", "Николаевич", (byte) 19);

        List<User> users = userService.getAll();

        for (User u : users) {
            System.out.println (u);
        }

        userService.cleanTable();
        userService.dropTable();
    }
}
