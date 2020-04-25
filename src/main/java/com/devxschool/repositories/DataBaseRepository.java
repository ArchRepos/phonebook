package com.devxschool.repositories;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseRepository {
    private static String url = "jdbc:mysql://localhost:3306/phonebook";
    private static String username = "ph_user";
    private static String password = "ph_password";

    private static MysqlDataSource dataSource = new MysqlDataSource();

    private static Connection connection = null;

    public static Connection connect() throws SQLException {
        dataSource.setUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);

        connection = dataSource.getConnection();
        return connection;
    }

    public static void close() throws SQLException {
        connection.close();
    }
}
