package com.healthcare.service;

import com.healthcare.model.Appointment;
import com.healthcare.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByStatus(String status) {

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException(
                    "Appointment status cannot be empty.");
        }

        return appointmentRepository.findByStatus(status);
    }
}