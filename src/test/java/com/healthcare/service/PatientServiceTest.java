package com.healthcare.service;

import com.healthcare.exception.PatientNotFoundException;
import com.healthcare.model.Patient;
import com.healthcare.repository.PatientRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    @Test
    void shouldReturnPatientWhenIdExists() {

        PatientRepository repository = mock(PatientRepository.class);

        Patient patient = new Patient();
        patient.setPatientId(1);
        patient.setFirstName("Aarav");
        patient.setLastName("Sharma");
        patient.setDateOfBirth(LocalDate.of(1995, 4, 12));
        patient.setGender("MALE");
        patient.setBloodGroup("O+");

        when(repository.findAll()).thenReturn(List.of(patient));

        PatientService service = new PatientService(repository);

        Patient result = service.getPatientById(1);

        assertNotNull(result);
        assertEquals(1, result.getPatientId());
        assertEquals("Aarav", result.getFirstName());

        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldThrowExceptionWhenPatientDoesNotExist() {

        PatientRepository repository = mock(PatientRepository.class);

        when(repository.findAll()).thenReturn(List.of());

        PatientService service = new PatientService(repository);

        PatientNotFoundException exception =
                assertThrows(
                        PatientNotFoundException.class,
                        () -> service.getPatientById(999)
                );

        assertEquals(
                "Patient not found with ID: 999",
                exception.getMessage()
        );

        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldRejectInvalidPatientId() {

        PatientRepository repository = mock(PatientRepository.class);

        PatientService service = new PatientService(repository);

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> service.getPatientById(0)
                );

        assertEquals(
                "Patient ID must be greater than zero.",
                exception.getMessage()
        );

        verify(repository, never()).findAll();
    }
}