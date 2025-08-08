package com.sabintarba.heartbank.privateapi.core.audit.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sabintarba.heartbank.privateapi.core.audit.AuditType;
import com.sabintarba.heartbank.privateapi.core.audit.dto.response.AuditLogResponse;
import com.sabintarba.heartbank.privateapi.core.audit.entity.AuditLogEntity;
import com.sabintarba.heartbank.privateapi.core.audit.entity.AuditLogParamEntity;
import com.sabintarba.heartbank.privateapi.core.audit.mapper.AuditLogMapper;
import com.sabintarba.heartbank.privateapi.core.audit.repository.AuditLogRepository;
import com.sabintarba.heartbank.privateapi.generic.PaginatedResponse;

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
    public void audit(AuditType auditType, String auditAction, List<AuditLogParamEntity> params) {
        AuditLogEntity auditLog = new AuditLogEntity();

        auditLog.setAuditType(auditType);
        auditLog.setAuditAction(auditAction);
        auditLog.setTimestampCreated(LocalDateTime.now());

        for (AuditLogParamEntity param : params) {
            param.setAuditLog(auditLog);
        }

        auditLog.setParams(params);

        auditLogRepository.save(auditLog);
    }

    /**
     * Cleanup audit logs.
     * @param retentionDays configuration parameter specified in audit.retention.days.
     */


    /**
     * Cleanup audit logs.
     * @param retentionDays configuration parameter specified in audit.retention.days.
     * @return AuditCleanupResult object
     */
    @Transactional
    public AuditCleanupResult cleanup(Integer retentionDays) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime cutoffDateTime = now.minusDays(retentionDays);

        Integer deletedLogParamCount = auditLogRepository.deleteOldLogParams(cutoffDateTime);
        Integer deletedLogCount = auditLogRepository.deleteOldLogs(cutoffDateTime);

        AuditLogParamEntity param1 = new AuditLogParamEntity();
        param1.setTimestampCreated(now);
        param1.setKey("deletedLogCount");
        param1.setValue(deletedLogCount.toString());

        AuditLogParamEntity param2 = new AuditLogParamEntity();
        param2.setTimestampCreated(now);
        param2.setKey("deletedLogParamCount");
        param2.setValue(deletedLogParamCount.toString());

        AuditLogParamEntity param3 = new AuditLogParamEntity();
        param3.setTimestampCreated(now);
        param3.setKey("retentionDays");
        param3.setValue(retentionDays.toString());

        AuditLogParamEntity param4 = new AuditLogParamEntity();
        param4.setTimestampCreated(now);
        param4.setKey("cutoffDateTime");
        param4.setValue(cutoffDateTime.toString());
        
        audit(AuditType.CLEANUP, "CLEANUP_OLDER_AUDIT_LOGS", List.of(param1, param2, param3, param4));

        return new AuditCleanupResult(deletedLogCount, deletedLogParamCount);
    }

    /**
     * 
     * @param pageable used for pagination
     * @return list of audit logs
     */
    public PaginatedResponse<AuditLogResponse> getPaginatedAuditLogs(Pageable pageable) {
        Page<AuditLogEntity> auditLogsPaginated = auditLogRepository.findAll(pageable);

        PaginatedResponse.PaginationMeta meta = new PaginatedResponse.PaginationMeta();
        
        meta.setPage(auditLogsPaginated.getNumber());
        meta.setSize(auditLogsPaginated.getSize());
        meta.setTotalPages(auditLogsPaginated.getTotalPages());
        meta.setTotalElements(auditLogsPaginated.getTotalElements());
        meta.setHasNext(auditLogsPaginated.hasNext());
        meta.setHasPrevious(auditLogsPaginated.hasPrevious());

        PaginatedResponse<AuditLogResponse> response = new PaginatedResponse<>();
        response.setData(auditLogsPaginated.stream().map(AuditLogMapper::toResponse).toList());
        response.setPagination(meta);

        return response;
    }
}