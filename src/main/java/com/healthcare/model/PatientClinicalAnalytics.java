package com.healthcare.model;

public class PatientClinicalAnalytics {

    private int patientId;
    private String patientName;
    private int totalEncounters;
    private int totalDiagnoses;
    private int totalLabResults;

    public PatientClinicalAnalytics(
            int patientId,
            String patientName,
            int totalEncounters,
            int totalDiagnoses,
            int totalLabResults) {

        this.patientId = patientId;
        this.patientName = patientName;
        this.totalEncounters = totalEncounters;
        this.totalDiagnoses = totalDiagnoses;
        this.totalLabResults = totalLabResults;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getTotalEncounters() {
        return totalEncounters;
    }

    public int getTotalDiagnoses() {
        return totalDiagnoses;
    }

    public int getTotalLabResults() {
        return totalLabResults;
    }
}