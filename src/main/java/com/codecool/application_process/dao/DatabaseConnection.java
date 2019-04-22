package com.codecool.application_process.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public Connection getConnection() {
        Connection connection = null;
        String URL = "jdbc:postgresql://localhost:5432/application-process";
        String USER = "postgres";
        String PASSWORD = "123";

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}