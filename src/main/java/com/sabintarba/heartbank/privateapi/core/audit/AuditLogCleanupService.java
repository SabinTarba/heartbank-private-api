package com.sabintarba.heartbank.privateapi.core.audit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

/**
 * Service class for cleaningup the audit logs.
 * 
 * @author Sabin Tarba, sabintarba01@gmail.com
 */
@Service
public class AuditLogCleanupService {

    private final AuditLogService auditLogService;

    @Value("${audit.retention.days}")
    private Integer retentionDays;

    public AuditLogCleanupService(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    /**
     * Cleanup audit logs and audit params which are older than configuration parameter specified in audit.retention.days.
     * The cleanup is scheduled every day at 02:00 AM.
     */
    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional
    public void cleanUpOldAuditLogs() {
        auditLogService.cleanup(retentionDays);
    }
}
