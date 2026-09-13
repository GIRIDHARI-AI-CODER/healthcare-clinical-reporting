package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorAppointmentReportRepository {

    public void printDoctorAppointmentSummary() {

        String sql = """
                SELECT
                    d.DOCTOR_ID,
                    d.DOCTOR_NAME,
                    d.SPECIALIZATION,
                    COUNT(a.APPOINTMENT_ID) AS APPOINTMENT_COUNT
                FROM DOCTOR d
                LEFT JOIN APPOINTMENT a
                    ON d.DOCTOR_ID = a.DOCTOR_ID
                GROUP BY
                    d.DOCTOR_ID,
                    d.DOCTOR_NAME,
                    d.SPECIALIZATION
                ORDER BY APPOINTMENT_COUNT DESC
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println();
            System.out.println("===== DOCTOR APPOINTMENT SUMMARY =====");

            while (resultSet.next()) {

                System.out.println("----------------------------------------");

                System.out.println(
                        "Doctor ID      : " +
                        resultSet.getInt("DOCTOR_ID")
                );

                System.out.println(
                        "Doctor Name    : " +
                        resultSet.getString("DOCTOR_NAME")
                );

                System.out.println(
                        "Specialization : " +
                        resultSet.getString("SPECIALIZATION")
                );

                System.out.println(
                        "Appointments   : " +
                        resultSet.getInt("APPOINTMENT_COUNT")
                );
            }

            System.out.println("=======================================");

        } catch (SQLException e) {
            System.out.println(
                    "Error generating doctor appointment summary."
            );
            e.printStackTrace();
        }
    }
}