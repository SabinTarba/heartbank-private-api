package com.sabintarba.heartbank.privateapi.core.audit.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabintarba.heartbank.privateapi.core.audit.dto.response.AuditLogResponse;
import com.sabintarba.heartbank.privateapi.core.audit.service.AuditLogService;
import com.sabintarba.heartbank.privateapi.generic.PaginatedResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller class for audit module.
 * 
 * @author Sabin Tarba, sabintarba01@gmail.com
 */
@RestController
@RequestMapping("/api/v1/audit")
@Slf4j
public class AuditLogController {
    
    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    /**
     * 
     * @param pageable used for pagination
     * @return list of audit logs for API client
     */
    @GetMapping("/logs")
    public ResponseEntity<PaginatedResponse<AuditLogResponse>> getAuditLogs(@PageableDefault(size = 10, sort = "timestampCreated", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info(String.format("GET /api/v1/audit/logs - page number: %d, size: %d, sort: %s", pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().toList().toString()));

        PaginatedResponse<AuditLogResponse> paginatedAuditLogs = auditLogService.getPaginatedAuditLogs(pageable);

        return ResponseEntity.ok(paginatedAuditLogs);
    }
}
