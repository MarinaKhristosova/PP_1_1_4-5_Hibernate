package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static Connection connection = null;
    private static SessionFactory sessionFactory = null;
    public final static String URL = "jdbc:mysql://localhost:3306/DB";
    final static String USERNAME = "root";
    final static String PASSWORD = "rootroot";
    final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    public final static String TABLE_NAME = "Users";


    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection created");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                DriverManager.getConnection(URL, USERNAME, PASSWORD);
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException sqlExcept) {
            System.out.println(sqlExcept.getMessage());
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();

                properties.setProperty("hibernate.connection.url", URL);
                properties.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
                properties.setProperty("hibernate.connection.username", USERNAME);
                properties.setProperty("hibernate.connection.password", PASSWORD);
                properties.setProperty("hibernate.connection.driver_class", DRIVER);
                properties.setProperty("hibernate.current_session_context_class", "thread");
                properties.setProperty("show_sql", "true");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("Connection open");
            } catch (Exception e) {
                System.out.println("Connection failed");
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (!getSessionFactory().isClosed()) {
            getSessionFactory().close();
        }
    }
}
