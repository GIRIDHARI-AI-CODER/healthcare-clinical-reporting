package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;
import com.healthcare.model.Doctor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorRepository {

    public List<Doctor> findAll() {
        List<Doctor> doctors = new ArrayList<>();

        String sql = """
                SELECT DOCTOR_ID,
                       DEPARTMENT_ID,
                       DOCTOR_NAME,
                       SPECIALIZATION,
                       PHONE,
                       EMAIL,
                       JOINING_DATE,
                       STATUS
                FROM DOCTOR
                ORDER BY DOCTOR_ID
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                doctors.add(mapDoctor(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch doctors", e);
        }

        return doctors;
    }

    public Doctor findById(int doctorId) {
        String sql = """
                SELECT DOCTOR_ID,
                       DEPARTMENT_ID,
                       DOCTOR_NAME,
                       SPECIALIZATION,
                       PHONE,
                       EMAIL,
                       JOINING_DATE,
                       STATUS
                FROM DOCTOR
                WHERE DOCTOR_ID = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, doctorId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapDoctor(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch doctor", e);
        }

        return null;
    }

    private Doctor mapDoctor(ResultSet resultSet) throws SQLException {
        Doctor doctor = new Doctor();

        doctor.setDoctorId(resultSet.getInt("DOCTOR_ID"));
        doctor.setDepartmentId(resultSet.getInt("DEPARTMENT_ID"));
        doctor.setDoctorName(resultSet.getString("DOCTOR_NAME"));
        doctor.setSpecialization(resultSet.getString("SPECIALIZATION"));
        doctor.setPhone(resultSet.getString("PHONE"));
        doctor.setEmail(resultSet.getString("EMAIL"));

        if (resultSet.getDate("JOINING_DATE") != null) {
            doctor.setJoiningDate(
                    resultSet.getDate("JOINING_DATE").toLocalDate()
            );
        }

        doctor.setStatus(resultSet.getString("STATUS"));

        return doctor;
    }
}