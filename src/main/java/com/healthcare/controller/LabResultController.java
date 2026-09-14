package com.healthcare.controller;

import com.healthcare.model.LabResultReport;
import com.healthcare.service.LabResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class LabResultController {

    private final LabResultService labResultService;

    public LabResultController(LabResultService labResultService) {
        this.labResultService = labResultService;
    }

    @GetMapping("/{patientId}/lab-results")
    public List<LabResultReport> getLabResults(
            @PathVariable int patientId) {

        return labResultService.getLabResultsByPatientId(patientId);
    }
}