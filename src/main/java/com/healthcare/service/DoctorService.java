package com.healthcare.service;

import com.healthcare.model.Doctor;
import com.healthcare.repository.DoctorRepository;

import java.util.List;

public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService() {
        this.doctorRepository = new DoctorRepository();
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(int doctorId) {

        List<Doctor> doctors = doctorRepository.findAll();

        for (Doctor doctor : doctors) {
            if (doctor.getDoctorId() == doctorId) {
                return doctor;
            }
        }

        return null;
    }
}