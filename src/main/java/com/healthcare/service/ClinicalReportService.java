package com.healthcare.service;

import com.healthcare.repository.ClinicalReportRepository;

public class ClinicalReportService {

    private final ClinicalReportRepository clinicalReportRepository;

    public ClinicalReportService() {
        this.clinicalReportRepository = new ClinicalReportRepository();
    }

    public void generatePatientClinicalReport(int patientId) {
        if (patientId <= 0) {
            System.out.println("Invalid patient ID.");
            return;
        }

        System.out.println();
        System.out.println("========== PATIENT CLINICAL REPORT ==========");
        clinicalReportRepository.printPatientClinicalReport(patientId);
        System.out.println("=============================================");
    }
}