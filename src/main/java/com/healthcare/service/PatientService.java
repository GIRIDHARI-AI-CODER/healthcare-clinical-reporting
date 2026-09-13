package com.healthcare.service;

import com.healthcare.model.Patient;
import com.healthcare.repository.PatientRepository;

import java.util.List;

public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService() {
        this.patientRepository = new PatientRepository();
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(int patientId) {

        List<Patient> patients = patientRepository.findAll();

        for (Patient patient : patients) {
            if (patient.getPatientId() == patientId) {
                return patient;
            }
        }

        return null;
    }
}