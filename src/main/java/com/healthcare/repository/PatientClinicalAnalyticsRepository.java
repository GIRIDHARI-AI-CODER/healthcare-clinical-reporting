package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;
import com.healthcare.model.PatientClinicalAnalytics;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientClinicalAnalyticsRepository {

    public List<PatientClinicalAnalytics> getPatientClinicalAnalytics() {

        String sql = """
                SELECT
                    p.PATIENT_ID,
                    p.FIRST_NAME || ' ' || p.LAST_NAME AS PATIENT_NAME,
                    COUNT(DISTINCT e.ENCOUNTER_ID) AS TOTAL_ENCOUNTERS,
                    COUNT(DISTINCT d.DIAGNOSIS_ID) AS TOTAL_DIAGNOSES,
                    COUNT(DISTINCT lr.LAB_RESULT_ID) AS TOTAL_LAB_RESULTS
                FROM PATIENT p
                LEFT JOIN ENCOUNTER e
                    ON p.PATIENT_ID = e.PATIENT_ID
                LEFT JOIN DIAGNOSIS d
                    ON e.ENCOUNTER_ID = d.ENCOUNTER_ID
                LEFT JOIN LAB_RESULT lr
                    ON e.ENCOUNTER_ID = lr.ENCOUNTER_ID
                GROUP BY
                    p.PATIENT_ID,
                    p.FIRST_NAME,
                    p.LAST_NAME
                ORDER BY
                    TOTAL_ENCOUNTERS DESC,
                    TOTAL_DIAGNOSES DESC,
                    p.PATIENT_ID
                """;

        List<PatientClinicalAnalytics> results = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                PatientClinicalAnalytics analytics =
                        new PatientClinicalAnalytics(
                                resultSet.getInt("PATIENT_ID"),
                                resultSet.getString("PATIENT_NAME"),
                                resultSet.getInt("TOTAL_ENCOUNTERS"),
                                resultSet.getInt("TOTAL_DIAGNOSES"),
                                resultSet.getInt("TOTAL_LAB_RESULTS")
                        );

                results.add(analytics);
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to generate patient clinical analytics",
                    e
            );
        }

        return results;
    }
}