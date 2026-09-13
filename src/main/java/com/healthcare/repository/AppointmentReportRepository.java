package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentReportRepository {

    public void printAppointmentReport() {

        String sql = """
                SELECT
                    a.APPOINTMENT_ID,
                    p.FIRST_NAME || ' ' || p.LAST_NAME AS PATIENT_NAME,
                    d.DOCTOR_NAME,
                    a.APPOINTMENT_TIME,
                    a.REASON,
                    a.STATUS
                FROM APPOINTMENT a
                JOIN PATIENT p
                    ON a.PATIENT_ID = p.PATIENT_ID
                JOIN DOCTOR d
                    ON a.DOCTOR_ID = d.DOCTOR_ID
                ORDER BY a.APPOINTMENT_TIME
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println();
            System.out.println("========== APPOINTMENT REPORT ==========");

            while (resultSet.next()) {

                System.out.println("----------------------------------------");

                System.out.println(
                        "Appointment ID : " +
                        resultSet.getInt("APPOINTMENT_ID")
                );

                System.out.println(
                        "Patient        : " +
                        resultSet.getString("PATIENT_NAME")
                );

                System.out.println(
                        "Doctor         : " +
                        resultSet.getString("DOCTOR_NAME")
                );

                System.out.println(
                        "Appointment    : " +
                        resultSet.getTimestamp("APPOINTMENT_TIME")
                );

                System.out.println(
                        "Reason         : " +
                        resultSet.getString("REASON")
                );

                System.out.println(
                        "Status         : " +
                        resultSet.getString("STATUS")
                );
            }

            System.out.println("========================================");

        } catch (SQLException e) {
            System.out.println("Error generating appointment report.");
            e.printStackTrace();
        }
    }
}