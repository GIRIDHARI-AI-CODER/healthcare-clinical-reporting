package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;
import com.healthcare.model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

                Appointment appointment = new Appointment();

                appointment.setAppointmentId(
                        resultSet.getInt("APPOINTMENT_ID")
                );

                appointment.setPatientId(
                        resultSet.getInt("PATIENT_ID")
                );

                appointment.setDoctorId(
                        resultSet.getInt("DOCTOR_ID")
                );

                if (resultSet.getTimestamp("APPOINTMENT_TIME") != null) {
                    appointment.setAppointmentTime(
                            resultSet.getTimestamp("APPOINTMENT_TIME")
                                    .toLocalDateTime()
                    );
                }

                appointment.setReason(
                        resultSet.getString("REASON")
                );

                appointment.setStatus(
                        resultSet.getString("STATUS")
                );

                appointments.add(appointment);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error fetching appointments from database."
            );
            e.printStackTrace();
        }

        return appointments;
    }
}