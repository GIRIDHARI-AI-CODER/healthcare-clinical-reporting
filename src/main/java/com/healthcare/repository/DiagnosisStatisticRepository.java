package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;
import com.healthcare.model.DiagnosisStatistic;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DiagnosisStatisticRepository {

    public List<DiagnosisStatistic> getDiagnosisStatistics() {

        List<DiagnosisStatistic> statistics = new ArrayList<>();

        String sql = """
                SELECT
                    DIAGNOSIS_NAME,
                    COUNT(DISTINCT ENCOUNTER_ID) AS PATIENT_COUNT
                FROM DIAGNOSIS
                GROUP BY DIAGNOSIS_NAME
                ORDER BY PATIENT_COUNT DESC, DIAGNOSIS_NAME
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                DiagnosisStatistic statistic = new DiagnosisStatistic();

                statistic.setDiagnosisName(
                        resultSet.getString("DIAGNOSIS_NAME"));

                statistic.setPatientCount(
                        resultSet.getInt("PATIENT_COUNT"));

                statistics.add(statistic);
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to fetch diagnosis statistics.", e);
        }

        return statistics;
    }
}