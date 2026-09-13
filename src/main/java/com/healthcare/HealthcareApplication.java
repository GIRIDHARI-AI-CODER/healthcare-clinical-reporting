package com.healthcare;

import com.healthcare.service.ClinicalReportService;

public class HealthcareApplication {

    public static void main(String[] args) {

        ClinicalReportService reportService =
                new ClinicalReportService();

        // Generate clinical report for Patient ID 1
        reportService.generatePatientClinicalReport(1);
    }
}