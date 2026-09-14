package com.healthcare.service;

import com.healthcare.model.PatientClinicalAnalytics;
import com.healthcare.repository.PatientClinicalAnalyticsRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientClinicalAnalyticsServiceTest {

    @Test
    void shouldReturnPatientClinicalAnalytics() {

        PatientClinicalAnalyticsRepository repository =
                mock(PatientClinicalAnalyticsRepository.class);

        PatientClinicalAnalyticsService service =
                new PatientClinicalAnalyticsService(repository);

        List<PatientClinicalAnalytics> expected = List.of(
                new PatientClinicalAnalytics(
                        1,
                        "Aarav Sharma",
                        1,
                        1,
                        1
                ),
                new PatientClinicalAnalytics(
                        6,
                        "Sneha Mohanty",
                        0,
                        0,
                        0
                )
        );

        when(repository.getPatientClinicalAnalytics())
                .thenReturn(expected);

        List<PatientClinicalAnalytics> actual =
                service.getPatientClinicalAnalytics();

        assertNotNull(actual);
        assertEquals(2, actual.size());

        assertEquals(1, actual.get(0).getPatientId());
        assertEquals(
                "Aarav Sharma",
                actual.get(0).getPatientName()
        );
        assertEquals(
                1,
                actual.get(0).getTotalEncounters()
        );
        assertEquals(
                1,
                actual.get(0).getTotalDiagnoses()
        );
        assertEquals(
                1,
                actual.get(0).getTotalLabResults()
        );

        assertEquals(6, actual.get(1).getPatientId());
        assertEquals(
                "Sneha Mohanty",
                actual.get(1).getPatientName()
        );
        assertEquals(
                0,
                actual.get(1).getTotalEncounters()
        );
        assertEquals(
                0,
                actual.get(1).getTotalDiagnoses()
        );
        assertEquals(
                0,
                actual.get(1).getTotalLabResults()
        );

        verify(repository, times(1))
                .getPatientClinicalAnalytics();
    }
}