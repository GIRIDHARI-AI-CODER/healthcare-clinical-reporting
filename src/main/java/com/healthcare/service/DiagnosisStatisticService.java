package com.healthcare.service;

import com.healthcare.model.DiagnosisStatistic;
import com.healthcare.repository.DiagnosisStatisticRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisStatisticService {

    private final DiagnosisStatisticRepository diagnosisStatisticRepository;

    public DiagnosisStatisticService(
            DiagnosisStatisticRepository diagnosisStatisticRepository) {
        this.diagnosisStatisticRepository = diagnosisStatisticRepository;
    }

    public List<DiagnosisStatistic> getDiagnosisStatistics() {
        return diagnosisStatisticRepository.getDiagnosisStatistics();
    }
}