package com.healthcare.service;

import com.healthcare.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void logEvent(
            String tableName,
            String actionType,
            int recordId,
            String changedBy,
            String details) {

        if (tableName == null || tableName.isBlank()) {
            throw new IllegalArgumentException("Table name is required");
        }

        if (actionType == null || actionType.isBlank()) {
            throw new IllegalArgumentException("Action type is required");
        }

        if (recordId <= 0) {
            throw new IllegalArgumentException("Record ID must be greater than 0");
        }

        if (changedBy == null || changedBy.isBlank()) {
            throw new IllegalArgumentException("Changed by is required");
        }

        auditLogRepository.logEvent(
                tableName,
                actionType.toUpperCase(),
                recordId,
                changedBy,
                details
        );
    }
}