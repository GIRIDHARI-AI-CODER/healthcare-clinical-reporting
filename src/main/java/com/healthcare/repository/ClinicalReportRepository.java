package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;
import com.healthcare.model.ClinicalReport;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClinicalReportRepository {

    public List<ClinicalReport> findPatientClinicalReport(int patientId) {

        List<ClinicalReport> reports = new ArrayList<>();

        String sql = """
                SELECT
                    p.PATIENT_ID,
                    p.FIRST_NAME || ' ' || p.LAST_NAME AS PATIENT_NAME,
                    p.GENDER,
                    p.BLOOD_GROUP,
                    e.ENCOUNTER_ID,
                    e.ENCOUNTER_TIME,
                    d.DOCTOR_NAME,
                    dg.DIAGNOSIS_NAME,
                    dg.DIAGNOSIS_TYPE,
                    m.MEDICATION_NAME,
                    m.DOSAGE,
                    m.FREQUENCY
                FROM PATIENT p
                LEFT JOIN ENCOUNTER e
                    ON p.PATIENT_ID = e.PATIENT_ID
                LEFT JOIN DOCTOR d
                    ON e.DOCTOR_ID = d.DOCTOR_ID
                LEFT JOIN DIAGNOSIS dg
                    ON e.ENCOUNTER_ID = dg.ENCOUNTER_ID
                LEFT JOIN MEDICATION m
                    ON e.ENCOUNTER_ID = m.ENCOUNTER_ID
                WHERE p.PATIENT_ID = ?
                ORDER BY e.ENCOUNTER_TIME DESC
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    ClinicalReport report = new ClinicalReport();

                    report.setPatientId(resultSet.getInt("PATIENT_ID"));
                    report.setPatientName(resultSet.getString("PATIENT_NAME"));
                    report.setGender(resultSet.getString("GENDER"));
                    report.setBloodGroup(resultSet.getString("BLOOD_GROUP"));
                    report.setEncounterId(resultSet.getInt("ENCOUNTER_ID"));

                    if (resultSet.getTimestamp("ENCOUNTER_TIME") != null) {
                        report.setEncounterTime(
                                resultSet.getTimestamp("ENCOUNTER_TIME").toLocalDateTime()
                        );
                    }

                    report.setDoctorName(resultSet.getString("DOCTOR_NAME"));
                    report.setDiagnosisName(resultSet.getString("DIAGNOSIS_NAME"));
                    report.setDiagnosisType(resultSet.getString("DIAGNOSIS_TYPE"));
                    report.setMedicationName(resultSet.getString("MEDICATION_NAME"));
                    report.setDosage(resultSet.getString("DOSAGE"));
                    report.setFrequency(resultSet.getString("FREQUENCY"));

                    reports.add(report);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to fetch clinical report for patient ID: " + patientId,
                    e
            );
        }

        return reports;
    }

    public void printPatientClinicalReport(int patientId) {

        List<ClinicalReport> reports = findPatientClinicalReport(patientId);

        if (reports.isEmpty()) {
            System.out.println("No clinical report found for patient ID: " + patientId);
            return;
        }

        for (ClinicalReport report : reports) {

            System.out.println("Patient ID       : " + report.getPatientId());
            System.out.println("Patient Name     : " + report.getPatientName());
            System.out.println("Gender           : " + report.getGender());
            System.out.println("Blood Group      : " + report.getBloodGroup());
            System.out.println("Encounter ID     : " + report.getEncounterId());
            System.out.println("Encounter Time   : " + report.getEncounterTime());
            System.out.println("Doctor           : " + report.getDoctorName());
            System.out.println("Diagnosis        : " + report.getDiagnosisName());
            System.out.println("Diagnosis Type   : " + report.getDiagnosisType());
            System.out.println("Medication       : " + report.getMedicationName());
            System.out.println("Dosage           : " + report.getDosage());
            System.out.println("Frequency        : " + report.getFrequency());
            System.out.println("---------------------------------------------");
        }
    }
}