package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;
import com.healthcare.model.Patient;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientRepository {

    public List<Patient> findAll() {

        List<Patient> patients = new ArrayList<>();

        String sql = """
                SELECT
                    PATIENT_ID,
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
                patients.add(mapPatient(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to fetch patients from database.", e);
        }

        return patients;
    }

    public List<Patient> searchByName(String name) {

        List<Patient> patients = new ArrayList<>();

        String sql = """
                SELECT
                    PATIENT_ID,
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
                WHERE UPPER(FIRST_NAME) LIKE UPPER(?)
                   OR UPPER(LAST_NAME) LIKE UPPER(?)
                ORDER BY FIRST_NAME, LAST_NAME
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            String searchPattern = "%" + name.trim() + "%";

            statement.setString(1, searchPattern);
            statement.setString(2, searchPattern);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    patients.add(mapPatient(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to search patients.", e);
        }

        return patients;
    }

    private Patient mapPatient(ResultSet resultSet)
            throws SQLException {

        Patient patient = new Patient();

        patient.setPatientId(
                resultSet.getInt("PATIENT_ID"));

        patient.setFirstName(
                resultSet.getString("FIRST_NAME"));

        patient.setLastName(
                resultSet.getString("LAST_NAME"));

        if (resultSet.getDate("DATE_OF_BIRTH") != null) {
            patient.setDateOfBirth(
                    resultSet.getDate("DATE_OF_BIRTH").toLocalDate());
        }

        patient.setGender(
                resultSet.getString("GENDER"));

        patient.setPhone(
                resultSet.getString("PHONE"));

        patient.setEmail(
                resultSet.getString("EMAIL"));

        patient.setBloodGroup(
                resultSet.getString("BLOOD_GROUP"));

        patient.setAddress(
                resultSet.getString("ADDRESS"));

        patient.setStatus(
                resultSet.getString("STATUS"));

        return patient;
    }
}