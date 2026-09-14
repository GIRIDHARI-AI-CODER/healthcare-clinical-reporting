package com.healthcare.service;

import com.healthcare.model.DoctorAppointmentStatistic;
import com.healthcare.repository.DoctorAppointmentStatisticRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorAppointmentStatisticService {

    private final DoctorAppointmentStatisticRepository repository;

    public DoctorAppointmentStatisticService(
            DoctorAppointmentStatisticRepository repository) {
        this.repository = repository;
    }

    public List<DoctorAppointmentStatistic> getDoctorAppointmentStatistics() {
        return repository.findDoctorAppointmentStatistics();
    }
}