package com.healthcare.controller;

import com.healthcare.model.DoctorAppointmentStatistic;
import com.healthcare.service.DoctorAppointmentStatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard/doctor-appointments")
public class DoctorAppointmentStatisticController {

    private final DoctorAppointmentStatisticService service;

    public DoctorAppointmentStatisticController(
            DoctorAppointmentStatisticService service) {
        this.service = service;
    }

    @GetMapping
    public List<DoctorAppointmentStatistic> getDoctorAppointmentStatistics() {
        return service.getDoctorAppointmentStatistics();
    }
}