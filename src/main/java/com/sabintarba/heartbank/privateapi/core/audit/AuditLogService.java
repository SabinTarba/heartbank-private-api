package com.sabintarba.heartbank.privateapi.core.audit;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

/**
 * Service class for audit module.
 * 
 * @author Sabin Tarba, sabintarba01@gmail.com
 */
@Service
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
    public void audit(AuditType auditType, String auditAction, List<AuditLogParams> params) {
        AuditLog auditLog = new AuditLog();

        auditLog.setAuditType(auditType.getType());
        auditLog.setAuditAction(auditAction);
        auditLog.setTimestampCreated(LocalDateTime.now());
        auditLog.setParams(params);

        auditLogRepository.save(auditLog);
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