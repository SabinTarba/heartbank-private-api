package com.sabintarba.heartbank.privateapi.core.audit.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto For AuditLogParam entity for external exposure.
 * @author Sabin Tarba, sabintarba01@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuditLogParamResponse {
    
    private LocalDateTime timestampCreated;
    private String key;
    private String value;
    
}
