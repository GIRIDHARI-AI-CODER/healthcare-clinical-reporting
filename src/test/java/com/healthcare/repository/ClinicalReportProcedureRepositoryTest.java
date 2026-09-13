package com.healthcare.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ClinicalReportProcedureRepositoryTest {

    @Test
    void shouldExecutePatientClinicalSummaryProcedure() {

        ClinicalReportProcedureRepository repository =
                new ClinicalReportProcedureRepository();

        assertDoesNotThrow(() ->
                repository.getPatientClinicalSummary(1)
        );
    }
}