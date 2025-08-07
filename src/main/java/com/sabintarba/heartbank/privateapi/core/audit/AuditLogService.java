package com.sabintarba.heartbank.privateapi.core.audit;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class for audit module.
 * 
 * @author Sabin Tarba, sabintarba01@gmail.com
 */
@Service
@Slf4j
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    /**
     * 
     * @param auditType AudiType enum
     * @param auditAction audit action
     * @param params list of additional parameters
     */
    @Transactional
    public void audit(AuditType auditType, String auditAction, List<AuditLogParam> params) {
        AuditLog auditLog = new AuditLog();

        auditLog.setAuditType(auditType.getType());
        auditLog.setAuditAction(auditAction);
        auditLog.setTimestampCreated(LocalDateTime.now());

        for (AuditLogParam param : params) {
            param.setAuditLog(auditLog);
        }

        auditLog.setParams(params);

        auditLogRepository.save(auditLog);
    }

    /**
     * Cleanup audit logs.
     * @param retentionDays configuration parameter specified in audit.retention.days.
     */
    @Transactional
    public void cleanup(Integer retentionDays) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime cutoffDateTime = now.minusDays(retentionDays);

        Integer deletedLogsParametersCount = auditLogRepository.deleteOldLogsParameters(cutoffDateTime);
        Integer deletedLogsCount = auditLogRepository.deleteOldLogs(cutoffDateTime);

        AuditLogParam param1 = new AuditLogParam();
        param1.setTimestampCreated(now);
        param1.setKey("deletedLogsCount");
        param1.setValue(deletedLogsCount.toString());

        AuditLogParam param2 = new AuditLogParam();
        param2.setTimestampCreated(now);
        param2.setKey("deletedLogsParametersCount");
        param2.setValue(deletedLogsParametersCount.toString());

        AuditLogParam param3 = new AuditLogParam();
        param3.setTimestampCreated(now);
        param3.setKey("retentionDays");
        param3.setValue(retentionDays.toString());

        AuditLogParam param4 = new AuditLogParam();
        param4.setTimestampCreated(now);
        param4.setKey("cutoffDateTime");
        param4.setValue(cutoffDateTime.toString());
        
        audit(AuditType.CLEANUP, "CLEANUP_OLDER_AUDIT_LOGS", List.of(param1, param2, param3, param4));

        String logMessage = String.format(
            "Audit cleanup executed at %s, deleting logs older than %s. Deleted audit logs: %d, deleted audit logs parameters: %d",
            now.toString(), cutoffDateTime.toString(), deletedLogsCount, deletedLogsParametersCount 
        );

        log.info(logMessage);
    }

    /**
     * 
     * @param pageable used for pagination
     * @return list of audit logs
     */
    public Page<AuditLog> getAuditLogs(Pageable pageable) {
        return auditLogRepository.findAll(pageable);
    }
}