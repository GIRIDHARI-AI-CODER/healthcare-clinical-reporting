package com.healthcare.service;

import com.healthcare.model.DepartmentClinicalStatistic;
import com.healthcare.repository.DepartmentClinicalStatisticRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentClinicalStatisticService {

    private final DepartmentClinicalStatisticRepository repository;

    public DepartmentClinicalStatisticService(
            DepartmentClinicalStatisticRepository repository) {
        this.repository = repository;
    }

    public List<DepartmentClinicalStatistic> getDepartmentClinicalStatistics() {
        return repository.findDepartmentClinicalStatistics();
    }
}