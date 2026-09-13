package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;
import com.healthcare.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository {

    public List<Department> findAll() {

        List<Department> departments = new ArrayList<>();

        String sql = """
                SELECT
                    DEPARTMENT_ID,
                    DEPARTMENT_NAME,
                    LOCATION,
                    CREATED_AT
                FROM DEPARTMENT
                ORDER BY DEPARTMENT_ID
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Department department = new Department();

                department.setDepartmentId(
                        resultSet.getInt("DEPARTMENT_ID")
                );

                department.setDepartmentName(
                        resultSet.getString("DEPARTMENT_NAME")
                );

                department.setLocation(
                        resultSet.getString("LOCATION")
                );

                if (resultSet.getTimestamp("CREATED_AT") != null) {
                    department.setCreatedAt(
                            resultSet.getTimestamp("CREATED_AT")
                                    .toLocalDateTime()
                    );
                }

                departments.add(department);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error fetching departments from database."
            );
            e.printStackTrace();
        }

        return departments;
    }
}