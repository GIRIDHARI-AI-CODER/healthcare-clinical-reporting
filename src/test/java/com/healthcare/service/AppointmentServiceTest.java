package com.healthcare.service;

import com.healthcare.model.Appointment;
import com.healthcare.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {

    @Test
    void shouldReturnAllAppointments() {

        AppointmentRepository repository =
                mock(AppointmentRepository.class);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(1);
        appointment.setPatientId(1);
        appointment.setDoctorId(1);
        appointment.setStatus("COMPLETED");

        when(repository.findAll())
                .thenReturn(List.of(appointment));

        AppointmentService service =
                new AppointmentService(repository);

        List<Appointment> result =
                service.getAllAppointments();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getAppointmentId());
        assertEquals("COMPLETED", result.get(0).getStatus());

        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldReturnAppointmentsByStatus() {

        AppointmentRepository repository =
                mock(AppointmentRepository.class);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(6);
        appointment.setPatientId(6);
        appointment.setDoctorId(1);
        appointment.setStatus("SCHEDULED");

        when(repository.findByStatus("SCHEDULED"))
                .thenReturn(List.of(appointment));

        AppointmentService service =
                new AppointmentService(repository);

        List<Appointment> result =
                service.getAppointmentsByStatus("SCHEDULED");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("SCHEDULED", result.get(0).getStatus());

        verify(repository, times(1))
                .findByStatus("SCHEDULED");
    }

    @Test
    void shouldRejectEmptyAppointmentStatus() {

        AppointmentRepository repository =
                mock(AppointmentRepository.class);

        AppointmentService service =
                new AppointmentService(repository);

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> service.getAppointmentsByStatus("")
                );

        assertEquals(
                "Appointment status cannot be empty.",
                exception.getMessage()
        );

        verify(repository, never()).findByStatus(anyString());
    }
}