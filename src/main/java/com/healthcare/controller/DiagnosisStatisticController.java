package com.healthcare.controller;

import com.healthcare.model.DiagnosisStatistic;
import com.healthcare.service.DiagnosisStatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DiagnosisStatisticController {

    private final DiagnosisStatisticService diagnosisStatisticService;

    public DiagnosisStatisticController(
            DiagnosisStatisticService diagnosisStatisticService) {
        this.diagnosisStatisticService = diagnosisStatisticService;
    }

    @GetMapping("/diagnoses")
    public List<DiagnosisStatistic> getDiagnosisStatistics() {
        return diagnosisStatisticService.getDiagnosisStatistics();
    }
}