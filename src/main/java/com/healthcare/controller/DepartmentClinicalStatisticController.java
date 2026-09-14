package com.healthcare.controller;

import com.healthcare.model.DepartmentClinicalStatistic;
import com.healthcare.service.DepartmentClinicalStatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard/department-clinical-statistics")
public class DepartmentClinicalStatisticController {

    private final DepartmentClinicalStatisticService service;

    public DepartmentClinicalStatisticController(
            DepartmentClinicalStatisticService service) {
        this.service = service;
    }

    @GetMapping
    public List<DepartmentClinicalStatistic> getDepartmentClinicalStatistics() {
        return service.getDepartmentClinicalStatistics();
    }
}