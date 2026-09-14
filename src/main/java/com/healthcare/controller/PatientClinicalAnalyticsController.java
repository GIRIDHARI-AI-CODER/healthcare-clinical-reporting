package com.healthcare.controller;

import com.healthcare.model.PatientClinicalAnalytics;
import com.healthcare.service.PatientClinicalAnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class PatientClinicalAnalyticsController {

    private final PatientClinicalAnalyticsService service;

    public PatientClinicalAnalyticsController(
            PatientClinicalAnalyticsService service) {
        this.service = service;
    }

    @GetMapping("/patient-clinical-analytics")
    public List<PatientClinicalAnalytics> getPatientClinicalAnalytics() {
        return service.getPatientClinicalAnalytics();
    }
}