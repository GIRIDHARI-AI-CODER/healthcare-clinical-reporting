package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClinicalReportProcedureRepository {

    public void getPatientClinicalSummary(int patientId) {

        String sql = "{call GET_PATIENT_CLINICAL_SUMMARY(?, ?)}";

        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            // Input parameter
            statement.setInt(1, patientId);

            // Output parameter - Oracle REF CURSOR
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            statement.execute();

            try (ResultSet resultSet =
                         (ResultSet) statement.getObject(2)) {

                boolean found = false;

                System.out.println();
                System.out.println("===== PL/SQL CLINICAL SUMMARY =====");

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

                System.out.println("===================================");
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error executing PL/SQL clinical summary."
            );
            e.printStackTrace();
        }
    }
}
