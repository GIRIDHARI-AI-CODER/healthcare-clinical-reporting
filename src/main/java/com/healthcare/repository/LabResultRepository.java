package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;
import com.healthcare.model.LabResultReport;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LabResultRepository {

    public List<LabResultReport> findLabResultsByPatientId(int patientId) {

        List<LabResultReport> results = new ArrayList<>();

        String sql = """
                SELECT
                    p.PATIENT_ID,
                    lr.LAB_RESULT_ID,
                    lt.TEST_NAME,
                    lr.RESULT_VALUE,
                    lt.UNIT,
                    lt.NORMAL_RANGE,
                    lr.RESULT_STATUS
                FROM PATIENT p
                JOIN ENCOUNTER e
                    ON p.PATIENT_ID = e.PATIENT_ID
                JOIN LAB_RESULT lr
                    ON e.ENCOUNTER_ID = lr.ENCOUNTER_ID
                JOIN LAB_TEST lt
                    ON lr.LAB_TEST_ID = lt.LAB_TEST_ID
                WHERE p.PATIENT_ID = ?
                ORDER BY lr.RESULT_DATE DESC
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    LabResultReport result = new LabResultReport();

                    result.setPatientId(
                            resultSet.getInt("PATIENT_ID"));

                    result.setLabResultId(
                            resultSet.getInt("LAB_RESULT_ID"));

                    result.setTestName(
                            resultSet.getString("TEST_NAME"));

                    result.setResultValue(
                            resultSet.getString("RESULT_VALUE"));

                    result.setUnit(
                            resultSet.getString("UNIT"));

                    result.setReferenceRange(
                            resultSet.getString("NORMAL_RANGE"));

                    result.setStatus(
                            resultSet.getString("RESULT_STATUS"));

                    results.add(result);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to fetch lab results for patient ID: "
                            + patientId,
                    e);
        }

        return results;
    }
}