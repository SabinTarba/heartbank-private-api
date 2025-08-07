package com.sabintarba.heartbank.privateapi.core.audit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabintarba.heartbank.privateapi.core.generic.PaginatedResponse;

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
    public ResponseEntity<PaginatedResponse<AuditLog>> getAuditLogs(@PageableDefault(size = 10, sort = "timestampCreated", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info(String.format("GET /api/v1/audit/logs - page number: %d, size: %d, sort: %s", pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().toList().toString()));

        Page<AuditLog> auditLogsPaginated = auditLogService.getAuditLogs(pageable);

        PaginatedResponse.PaginationMeta meta = new PaginatedResponse.PaginationMeta();
        
        meta.setPage(auditLogsPaginated.getNumber());
        meta.setSize(auditLogsPaginated.getSize());
        meta.setTotalPages(auditLogsPaginated.getTotalPages());
        meta.setTotalElements(auditLogsPaginated.getTotalElements());
        meta.setHasNext(auditLogsPaginated.hasNext());
        meta.setHasPrevious(auditLogsPaginated.hasPrevious());

        PaginatedResponse<AuditLog> response = new PaginatedResponse<>();
        response.setData(auditLogsPaginated.toList());
        response.setPagination(meta);

        return ResponseEntity.ok(response);
    }
}
