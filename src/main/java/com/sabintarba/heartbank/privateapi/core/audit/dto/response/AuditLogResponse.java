package com.sabintarba.heartbank.privateapi.core.audit.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.sabintarba.heartbank.privateapi.core.audit.AuditType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto For AuditLog entity for external exposure.
 * @author Sabin Tarba, sabintarba01@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuditLogResponse {

    private Long id;
    private AuditType auditType;
    private String auditAction;
    private LocalDateTime timestampCreated;
    private List<AuditLogParamResponse> params;

}
