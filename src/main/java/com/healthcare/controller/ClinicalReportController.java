package com.healthcare.controller;

import com.healthcare.model.ClinicalReport;
import com.healthcare.service.ClinicalReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class ClinicalReportController {

    private final ClinicalReportService clinicalReportService;

    public ClinicalReportController(ClinicalReportService clinicalReportService) {
        this.clinicalReportService = clinicalReportService;
    }

    @GetMapping("/{patientId}/clinical-report")
    public List<ClinicalReport> getClinicalReport(
            @PathVariable int patientId) {

        return clinicalReportService.getPatientClinicalReport(patientId);
    }
}