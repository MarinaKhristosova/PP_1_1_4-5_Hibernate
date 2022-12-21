package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
       // Util.getConnection();
        UserService userService
                = new UserServiceImpl();

       userService.createUsersTable();
        userService.saveUser("Marina", "Khristosova", (byte) 38);
        userService.saveUser("Alex", "Khristosov", (byte) 39);
        userService.saveUser("Mickail", "Khristosov", (byte) 11);
        userService.saveUser("Alex", "Khristosov", (byte) 14);
        userService.saveUser("Raisa", "Khristosova", (byte) 60);
        userService.getAllUsers();
        userService.removeUserById(3);
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeSessionFactory();
    }
}
