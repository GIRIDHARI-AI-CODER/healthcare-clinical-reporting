package com.healthcare.controller;

import com.healthcare.service.AuditLogService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/audit")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @PostMapping
    public String createAuditLog(
            @RequestParam String tableName,
            @RequestParam String actionType,
            @RequestParam int recordId,
            @RequestParam String changedBy,
            @RequestParam String details) {

        auditLogService.logEvent(
                tableName,
                actionType,
                recordId,
                changedBy,
                details
        );

        return "Audit log created successfully";
    }
}