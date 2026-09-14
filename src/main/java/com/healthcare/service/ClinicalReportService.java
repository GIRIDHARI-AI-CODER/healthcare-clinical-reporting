package com.healthcare.service;

import com.healthcare.model.ClinicalReport;
import com.healthcare.repository.ClinicalReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicalReportService {

    private final ClinicalReportRepository clinicalReportRepository;

    public ClinicalReportService(ClinicalReportRepository clinicalReportRepository) {
        this.clinicalReportRepository = clinicalReportRepository;
    }

    public List<ClinicalReport> getPatientClinicalReport(int patientId) {
        if (patientId <= 0) {
            throw new IllegalArgumentException("Invalid patient ID.");
        }

        return clinicalReportRepository.findPatientClinicalReport(patientId);
    }
}