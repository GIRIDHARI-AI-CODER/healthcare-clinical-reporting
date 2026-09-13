package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientEncounterReportRepository {

    public void printPatientEncounterSummary() {

        String sql = """
                SELECT
                    p.PATIENT_ID,
                    p.FIRST_NAME || ' ' || p.LAST_NAME AS PATIENT_NAME,
                    COUNT(e.ENCOUNTER_ID) AS ENCOUNTER_COUNT
                FROM PATIENT p
                LEFT JOIN ENCOUNTER e
                    ON p.PATIENT_ID = e.PATIENT_ID
                GROUP BY
                    p.PATIENT_ID,
                    p.FIRST_NAME,
                    p.LAST_NAME
                ORDER BY ENCOUNTER_COUNT DESC,
                         p.PATIENT_ID
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println();
            System.out.println("===== PATIENT ENCOUNTER SUMMARY =====");

            while (resultSet.next()) {

                System.out.println("----------------------------------------");

                System.out.println(
                        "Patient ID   : " +
                        resultSet.getInt("PATIENT_ID")
                );

                System.out.println(
                        "Patient Name : " +
                        resultSet.getString("PATIENT_NAME")
                );

                System.out.println(
                        "Encounters   : " +
                        resultSet.getInt("ENCOUNTER_COUNT")
                );
            }

            System.out.println("=======================================");

        } catch (SQLException e) {
            System.out.println(
                    "Error generating patient encounter summary."
            );
            e.printStackTrace();
        }
    }
}