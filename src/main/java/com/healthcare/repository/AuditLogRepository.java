package com.healthcare.repository;

import com.healthcare.config.DatabaseConnection;

import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class AuditLogRepository {

    public void logEvent(
            String tableName,
            String actionType,
            int recordId,
            String changedBy,
            String details) {

        String sql = "{call LOG_AUDIT_EVENT(?, ?, ?, ?, ?)}";

        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, tableName);
            statement.setString(2, actionType);
            statement.setInt(3, recordId);
            statement.setString(4, changedBy);
            statement.setString(5, details);

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to write audit log",
                    e
            );
        }
    }
}