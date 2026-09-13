package com.healthcare;

import com.healthcare.model.Doctor;
import com.healthcare.repository.DoctorRepository;

import java.util.List;

public class DoctorRepositoryTest {

    public static void main(String[] args) {

        DoctorRepository repository = new DoctorRepository();

        List<Doctor> doctors = repository.findAll();

        System.out.println("Total doctors: " + doctors.size());

        for (Doctor doctor : doctors) {

            System.out.println(
                    doctor.getDoctorId() + " | " +
                    doctor.getDoctorName() + " | " +
                    doctor.getSpecialization() + " | " +
                    doctor.getEmail() + " | " +
                    doctor.getStatus()
            );
        }
    }
}