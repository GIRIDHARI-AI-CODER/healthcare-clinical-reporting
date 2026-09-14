package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;
import com.healthcare.model.DoctorAppointmentStatistic;
import oracle.jdbc.OracleTypes;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorClinicalPerformanceProcedureRepository {

    public List<DoctorAppointmentStatistic> getDoctorClinicalPerformance() {

        List<DoctorAppointmentStatistic> statistics = new ArrayList<>();

        String sql = "{call GET_DOCTOR_CLINICAL_PERFORMANCE(?)}";

        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.registerOutParameter(1, OracleTypes.CURSOR);

            statement.execute();

            try (ResultSet resultSet =
                         statement.getObject(1, ResultSet.class)) {

                while (resultSet.next()) {

                    DoctorAppointmentStatistic statistic =
                            new DoctorAppointmentStatistic();

                    statistic.setDoctorId(
                            resultSet.getInt("DOCTOR_ID")
                    );

                    statistic.setDoctorName(
                            resultSet.getString("DOCTOR_NAME")
                    );

                    statistic.setSpecialization(
                            resultSet.getString("SPECIALIZATION")
                    );

                    statistic.setTotalAppointments(
                            resultSet.getInt("TOTAL_APPOINTMENTS")
                    );

                    statistics.add(statistic);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to execute doctor clinical performance procedure",
                    e
            );
        }

        return statistics;
    }
}