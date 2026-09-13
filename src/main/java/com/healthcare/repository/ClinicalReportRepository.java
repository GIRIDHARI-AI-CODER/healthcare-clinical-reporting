package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClinicalReportRepository {

    public void printPatientClinicalReport(int patientId) {

        String sql = """
                SELECT
                    p.PATIENT_ID,
                    p.FIRST_NAME || ' ' || p.LAST_NAME AS PATIENT_NAME,
                    p.GENDER,
                    p.BLOOD_GROUP,
                    e.ENCOUNTER_ID,
                    e.ENCOUNTER_TIME,
                    d.DOCTOR_NAME,
                    dg.DIAGNOSIS_NAME,
                    dg.DIAGNOSIS_TYPE,
                    m.MEDICATION_NAME,
                    m.DOSAGE,
                    m.FREQUENCY
                FROM PATIENT p
                JOIN ENCOUNTER e
                    ON p.PATIENT_ID = e.PATIENT_ID
                JOIN DOCTOR d
                    ON e.DOCTOR_ID = d.DOCTOR_ID
                LEFT JOIN DIAGNOSIS dg
                    ON e.ENCOUNTER_ID = dg.ENCOUNTER_ID
                LEFT JOIN MEDICATION m
                    ON e.ENCOUNTER_ID = m.ENCOUNTER_ID
                WHERE p.PATIENT_ID = ?
                ORDER BY e.ENCOUNTER_TIME DESC
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);

            try (ResultSet resultSet = statement.executeQuery()) {

                boolean found = false;

                while (resultSet.next()) {
                    found = true;

                    System.out.println("----------------------------------------");

                    System.out.println(
                            "Patient ID     : "
                                    + resultSet.getInt("PATIENT_ID")
                    );

                    System.out.println(
                            "Patient Name   : "
                                    + resultSet.getString("PATIENT_NAME")
                    );

                    System.out.println(
                            "Gender         : "
                                    + resultSet.getString("GENDER")
                    );

                    System.out.println(
                            "Blood Group    : "
                                    + resultSet.getString("BLOOD_GROUP")
                    );

                    System.out.println(
                            "Encounter ID   : "
                                    + resultSet.getInt("ENCOUNTER_ID")
                    );

                    System.out.println(
                            "Encounter Time : "
                                    + resultSet.getTimestamp("ENCOUNTER_TIME")
                    );

                    System.out.println(
                            "Doctor         : "
                                    + resultSet.getString("DOCTOR_NAME")
                    );

                    System.out.println(
                            "Diagnosis      : "
                                    + resultSet.getString("DIAGNOSIS_NAME")
                    );

                    System.out.println(
                            "Diagnosis Type : "
                                    + resultSet.getString("DIAGNOSIS_TYPE")
                    );

                    System.out.println(
                            "Medication     : "
                                    + resultSet.getString("MEDICATION_NAME")
                    );

                    System.out.println(
                            "Dosage         : "
                                    + resultSet.getString("DOSAGE")
                    );

                    System.out.println(
                            "Frequency      : "
                                    + resultSet.getString("FREQUENCY")
                    );
                }

                if (!found) {
                    System.out.println(
                            "No clinical records found for patient ID: "
                                    + patientId
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error generating clinical report.");
            e.printStackTrace();
        }
    }
}