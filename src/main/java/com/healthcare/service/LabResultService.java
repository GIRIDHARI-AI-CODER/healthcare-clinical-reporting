package com.healthcare.service;

import com.healthcare.model.LabResultReport;
import com.healthcare.repository.LabResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabResultService {

    private final LabResultRepository labResultRepository;

    public LabResultService(LabResultRepository labResultRepository) {
        this.labResultRepository = labResultRepository;
    }

    public List<LabResultReport> getLabResultsByPatientId(int patientId) {

        if (patientId <= 0) {
            throw new IllegalArgumentException(
                    "Patient ID must be greater than zero.");
        }

        return labResultRepository.findLabResultsByPatientId(patientId);
    }
}