package com.healthcare.service;

import com.healthcare.exception.PatientNotFoundException;
import com.healthcare.model.Patient;
import com.healthcare.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(int patientId) {

        if (patientId <= 0) {
            throw new IllegalArgumentException(
                    "Patient ID must be greater than zero.");
        }

        List<Patient> patients = patientRepository.findAll();

        for (Patient patient : patients) {
            if (patient.getPatientId() == patientId) {
                return patient;
            }
        }

        throw new PatientNotFoundException(
                "Patient not found with ID: " + patientId);
    }

    public List<Patient> searchPatientsByName(String name) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Patient name cannot be empty.");
        }

        return patientRepository.searchByName(name);
    }
}