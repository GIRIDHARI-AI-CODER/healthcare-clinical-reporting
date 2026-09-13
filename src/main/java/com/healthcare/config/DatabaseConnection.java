package com.healthcare.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521/FREEPDB1";

    private static final String USER =
            "HEALTHCARE_FRESH";

    private static final String PASSWORD =
            "Giri@651234@dhari";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {

        try (Connection connection = getConnection()) {
            System.out.println("Oracle Database connected successfully!");
            System.out.println("Database: " +
                    connection.getMetaData().getDatabaseProductName());

        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}