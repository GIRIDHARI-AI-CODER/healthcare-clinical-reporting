package com.healthcare.service;

import com.healthcare.model.PatientClinicalAnalytics;
import com.healthcare.repository.PatientClinicalAnalyticsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientClinicalAnalyticsService {

    private final PatientClinicalAnalyticsRepository repository;

    public PatientClinicalAnalyticsService(
            PatientClinicalAnalyticsRepository repository) {
        this.repository = repository;
    }

    public List<PatientClinicalAnalytics> getPatientClinicalAnalytics() {
        return repository.getPatientClinicalAnalytics();
    }
}