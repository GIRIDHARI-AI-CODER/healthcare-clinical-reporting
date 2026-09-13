package com.healthcare;

import com.healthcare.repository.ClinicalReportProcedureRepository;

public class PlSqlClinicalReportTest {

    public static void main(String[] args) {

        ClinicalReportProcedureRepository repository =
                new ClinicalReportProcedureRepository();

        // Test PL/SQL procedure for Patient ID 1
        repository.getPatientClinicalSummary(1);
    }
}