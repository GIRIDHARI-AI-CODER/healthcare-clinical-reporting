package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;
import com.healthcare.model.Appointment;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AppointmentRepository {

    public List<Appointment> findAll() {

        List<Appointment> appointments = new ArrayList<>();

        String sql = """
                SELECT
                    APPOINTMENT_ID,
                    PATIENT_ID,
                    DOCTOR_ID,
                    APPOINTMENT_TIME,
                    REASON,
                    STATUS
                FROM APPOINTMENT
                ORDER BY APPOINTMENT_TIME
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                appointments.add(mapAppointment(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to fetch appointments from database.", e);
        }

        return appointments;
    }

    public List<Appointment> findByStatus(String status) {

        List<Appointment> appointments = new ArrayList<>();

        String sql = """
                SELECT
                    APPOINTMENT_ID,
                    PATIENT_ID,
                    DOCTOR_ID,
                    APPOINTMENT_TIME,
                    REASON,
                    STATUS
                FROM APPOINTMENT
                WHERE UPPER(STATUS) = UPPER(?)
                ORDER BY APPOINTMENT_TIME
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    appointments.add(mapAppointment(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to fetch appointments by status.", e);
        }

        return appointments;
    }

    private Appointment mapAppointment(ResultSet resultSet)
            throws SQLException {

        Appointment appointment = new Appointment();

        appointment.setAppointmentId(
                resultSet.getInt("APPOINTMENT_ID"));

        appointment.setPatientId(
                resultSet.getInt("PATIENT_ID"));

        appointment.setDoctorId(
                resultSet.getInt("DOCTOR_ID"));

        if (resultSet.getTimestamp("APPOINTMENT_TIME") != null) {
            appointment.setAppointmentTime(
                    resultSet.getTimestamp("APPOINTMENT_TIME")
                            .toLocalDateTime());
        }

        appointment.setReason(
                resultSet.getString("REASON"));

        appointment.setStatus(
                resultSet.getString("STATUS"));

        return appointment;
    }
}