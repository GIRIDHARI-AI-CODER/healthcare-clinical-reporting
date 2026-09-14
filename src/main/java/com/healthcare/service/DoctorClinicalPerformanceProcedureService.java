package com.healthcare.service;

import com.healthcare.model.DoctorAppointmentStatistic;
import com.healthcare.repository.DoctorClinicalPerformanceProcedureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorClinicalPerformanceProcedureService {

    private final DoctorClinicalPerformanceProcedureRepository repository;

    public DoctorClinicalPerformanceProcedureService(
            DoctorClinicalPerformanceProcedureRepository repository) {
        this.repository = repository;
    }

    public List<DoctorAppointmentStatistic> getDoctorClinicalPerformance() {
        return repository.getDoctorClinicalPerformance();
    }
}