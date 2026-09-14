package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;
import com.healthcare.model.DepartmentClinicalStatistic;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentClinicalStatisticRepository {

    public List<DepartmentClinicalStatistic> findDepartmentClinicalStatistics() {

        List<DepartmentClinicalStatistic> statistics = new ArrayList<>();

        String sql = """
                SELECT
                    dpt.DEPARTMENT_ID,
                    dpt.DEPARTMENT_NAME,

                    COUNT(DISTINCT d.DOCTOR_ID) AS TOTAL_DOCTORS,

                    COUNT(DISTINCT e.PATIENT_ID) AS TOTAL_PATIENTS,

                    COUNT(DISTINCT a.APPOINTMENT_ID) AS TOTAL_APPOINTMENTS,

                    COUNT(DISTINCT CASE
                        WHEN UPPER(a.STATUS) = 'COMPLETED'
                        THEN a.APPOINTMENT_ID
                    END) AS COMPLETED_APPOINTMENTS,

                    COUNT(DISTINCT CASE
                        WHEN UPPER(a.STATUS) = 'SCHEDULED'
                        THEN a.APPOINTMENT_ID
                    END) AS SCHEDULED_APPOINTMENTS,

                    COUNT(DISTINCT e.ENCOUNTER_ID) AS TOTAL_ENCOUNTERS

                FROM DEPARTMENT dpt

                LEFT JOIN DOCTOR d
                    ON dpt.DEPARTMENT_ID = d.DEPARTMENT_ID

                LEFT JOIN APPOINTMENT a
                    ON d.DOCTOR_ID = a.DOCTOR_ID

                LEFT JOIN ENCOUNTER e
                    ON d.DOCTOR_ID = e.DOCTOR_ID

                GROUP BY
                    dpt.DEPARTMENT_ID,
                    dpt.DEPARTMENT_NAME

                ORDER BY
                    dpt.DEPARTMENT_ID
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                DepartmentClinicalStatistic statistic =
                        new DepartmentClinicalStatistic();

                statistic.setDepartmentId(
                        resultSet.getInt("DEPARTMENT_ID")
                );

                statistic.setDepartmentName(
                        resultSet.getString("DEPARTMENT_NAME")
                );

                statistic.setTotalDoctors(
                        resultSet.getInt("TOTAL_DOCTORS")
                );

                statistic.setTotalPatients(
                        resultSet.getInt("TOTAL_PATIENTS")
                );

                statistic.setTotalAppointments(
                        resultSet.getInt("TOTAL_APPOINTMENTS")
                );

                statistic.setCompletedAppointments(
                        resultSet.getInt("COMPLETED_APPOINTMENTS")
                );

                statistic.setScheduledAppointments(
                        resultSet.getInt("SCHEDULED_APPOINTMENTS")
                );

                statistic.setTotalEncounters(
                        resultSet.getInt("TOTAL_ENCOUNTERS")
                );

                statistics.add(statistic);
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to fetch department clinical statistics",
                    e
            );
        }

        return statistics;
    }
}