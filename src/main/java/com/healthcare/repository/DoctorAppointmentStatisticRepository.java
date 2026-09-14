package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;
import com.healthcare.model.DoctorAppointmentStatistic;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorAppointmentStatisticRepository {

    public List<DoctorAppointmentStatistic> findDoctorAppointmentStatistics() {

        List<DoctorAppointmentStatistic> statistics = new ArrayList<>();

        String sql = """
                SELECT
                    d.DOCTOR_ID,
                    d.DOCTOR_NAME,
                    d.SPECIALIZATION,
                    COUNT(a.APPOINTMENT_ID) AS TOTAL_APPOINTMENTS
                FROM DOCTOR d
                LEFT JOIN APPOINTMENT a
                    ON d.DOCTOR_ID = a.DOCTOR_ID
                GROUP BY
                    d.DOCTOR_ID,
                    d.DOCTOR_NAME,
                    d.SPECIALIZATION
                ORDER BY
                    TOTAL_APPOINTMENTS DESC,
                    d.DOCTOR_ID
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                DoctorAppointmentStatistic statistic =
                        new DoctorAppointmentStatistic();

                statistic.setDoctorId(
                        resultSet.getInt("DOCTOR_ID")
                );

                statistic.setDoctorName(
                        resultSet.getString("DOCTOR_NAME")
                );

                statistic.setSpecialization(
                        resultSet.getString("SPECIALIZATION")
                );

                statistic.setTotalAppointments(
                        resultSet.getInt("TOTAL_APPOINTMENTS")
                );

                statistics.add(statistic);
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to fetch doctor appointment statistics",
                    e
            );
        }

        return statistics;
    }
}