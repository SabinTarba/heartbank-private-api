package com.sabintarba.heartbank.privateapi.core.audit.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sabintarba.heartbank.privateapi.core.audit.service.AuditCleanupResult;
import com.sabintarba.heartbank.privateapi.core.audit.service.AuditLogService;

import lombok.extern.slf4j.Slf4j;

/**
 * Component class for cleaningup the audit logs.
 * 
 * @author Sabin Tarba, sabintarba01@gmail.com
 */
@Component
@Slf4j
public class AuditLogCleanupScheduler {

    private final AuditLogService auditLogService;

    @Value("${audit.retention.days}")
    private Integer retentionDays;

    public AuditLogCleanupScheduler(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    /**
     * Cleanup audit logs and audit params which are older than configuration parameter specified in audit.retention.days.
     * The cleanup is scheduled every day at 02:00 AM.
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void cleanup() {
        AuditCleanupResult result = auditLogService.cleanup(retentionDays);
        
        log.info(
            "Audit cleanup executed. Deleted logs older that %d days. Deleted audit logs: %d, deleted audit logs parameters: %d",
            retentionDays, result.deletedLogsCount(), result.deletedLogsParamCount() 
        );
    }
}
