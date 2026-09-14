package com.healthcare.service;

import com.healthcare.model.Doctor;
import com.healthcare.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(int doctorId) {
        if (doctorId <= 0) {
            throw new IllegalArgumentException("Doctor ID must be greater than 0");
        }

        return doctorRepository.findById(doctorId);
    }
}