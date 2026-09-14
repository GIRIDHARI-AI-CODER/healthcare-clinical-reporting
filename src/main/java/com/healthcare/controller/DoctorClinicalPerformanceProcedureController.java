package com.healthcare.controller;

import com.healthcare.model.DoctorAppointmentStatistic;
import com.healthcare.service.DoctorClinicalPerformanceProcedureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard/doctor-clinical-performance")
public class DoctorClinicalPerformanceProcedureController {

    private final DoctorClinicalPerformanceProcedureService service;

    public DoctorClinicalPerformanceProcedureController(
            DoctorClinicalPerformanceProcedureService service) {
        this.service = service;
    }

    @GetMapping
    public List<DoctorAppointmentStatistic> getDoctorClinicalPerformance() {
        return service.getDoctorClinicalPerformance();
    }
}