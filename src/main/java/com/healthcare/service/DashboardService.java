package com.healthcare.service;

import com.healthcare.model.DashboardSummary;
import com.healthcare.repository.DashboardRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public DashboardSummary getDashboardSummary() {
        return dashboardRepository.getDashboardSummary();
    }
}