package com.healthcare.controller;

import com.healthcare.model.Patient;
import com.healthcare.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{patientId}")
    public Patient getPatientById(@PathVariable int patientId) {
        return patientService.getPatientById(patientId);
    }

    @GetMapping("/search")
    public List<Patient> searchPatients(
            @RequestParam String name) {

        return patientService.searchPatientsByName(name);
    }
}