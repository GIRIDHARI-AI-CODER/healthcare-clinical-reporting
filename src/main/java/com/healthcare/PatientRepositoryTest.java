package com.healthcare;

import com.healthcare.model.Patient;
import com.healthcare.repository.PatientRepository;

import java.util.List;

public class PatientRepositoryTest {

    public static void main(String[] args) {

        PatientRepository repository = new PatientRepository();

        List<Patient> patients = repository.findAll();

        System.out.println("Total patients: " + patients.size());

        for (Patient patient : patients) {
            System.out.println(
                    patient.getPatientId() + " | " +
                    patient.getFirstName() + " " +
                    patient.getLastName() + " | " +
                    patient.getGender() + " | " +
                    patient.getEmail()
            );
        }
    }
}