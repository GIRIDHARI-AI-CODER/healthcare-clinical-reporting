package com.healthcare.repository;

import com.healthcare.model.Patient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatientRepositoryTest {

    @Test
    void shouldFetchPatientsFromDatabase() {

        PatientRepository repository = new PatientRepository();

        List<Patient> patients = repository.findAll();

        assertNotNull(patients);
        assertEquals(8, patients.size());

        assertEquals("Aarav", patients.get(0).getFirstName());
        assertEquals("Sharma", patients.get(0).getLastName());
        assertEquals("MALE", patients.get(0).getGender());
    }
}