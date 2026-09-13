package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;
import com.healthcare.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository {

    public List<Patient> findAll() {

        List<Patient> patients = new ArrayList<>();

        String sql = """
                SELECT PATIENT_ID,
                       FIRST_NAME,
                       LAST_NAME,
                       DATE_OF_BIRTH,
                       GENDER,
                       PHONE,
                       EMAIL,
                       BLOOD_GROUP,
                       ADDRESS,
                       STATUS
                FROM PATIENT
                ORDER BY PATIENT_ID
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Patient patient = new Patient();

                patient.setPatientId(resultSet.getInt("PATIENT_ID"));
                patient.setFirstName(resultSet.getString("FIRST_NAME"));
                patient.setLastName(resultSet.getString("LAST_NAME"));

                if (resultSet.getDate("DATE_OF_BIRTH") != null) {
                    patient.setDateOfBirth(
                            resultSet.getDate("DATE_OF_BIRTH").toLocalDate()
                    );
                }

                patient.setGender(resultSet.getString("GENDER"));
                patient.setPhone(resultSet.getString("PHONE"));
                patient.setEmail(resultSet.getString("EMAIL"));
                patient.setBloodGroup(resultSet.getString("BLOOD_GROUP"));
                patient.setAddress(resultSet.getString("ADDRESS"));
                patient.setStatus(resultSet.getString("STATUS"));

                patients.add(patient);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching patients from database.");
            e.printStackTrace();
        }

        return patients;
    }
}