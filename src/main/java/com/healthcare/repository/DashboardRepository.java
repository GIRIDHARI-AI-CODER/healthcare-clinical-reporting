package com.healthcare.repository;


import com.healthcare.config.DatabaseConnection;
import com.healthcare.model.DashboardSummary;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DashboardRepository {

    public DashboardSummary getDashboardSummary() {

        DashboardSummary summary = new DashboardSummary();

        String sql = """
                SELECT
                    (SELECT COUNT(*) FROM PATIENT) AS TOTAL_PATIENTS,
                    (SELECT COUNT(*) FROM DOCTOR) AS TOTAL_DOCTORS,
                    (SELECT COUNT(*) FROM APPOINTMENT) AS TOTAL_APPOINTMENTS,
                    (SELECT COUNT(*)
                     FROM APPOINTMENT
                     WHERE UPPER(STATUS) = 'SCHEDULED') AS SCHEDULED_APPOINTMENTS,
                    (SELECT COUNT(*)
                     FROM APPOINTMENT
                     WHERE UPPER(STATUS) = 'COMPLETED') AS COMPLETED_APPOINTMENTS
                FROM DUAL
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {

                summary.setTotalPatients(
                        resultSet.getInt("TOTAL_PATIENTS"));

                summary.setTotalDoctors(
                        resultSet.getInt("TOTAL_DOCTORS"));

                summary.setTotalAppointments(
                        resultSet.getInt("TOTAL_APPOINTMENTS"));

                summary.setScheduledAppointments(
                        resultSet.getInt("SCHEDULED_APPOINTMENTS"));

                summary.setCompletedAppointments(
                        resultSet.getInt("COMPLETED_APPOINTMENTS"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to fetch dashboard summary.", e);
        }

        return summary;
    }
}