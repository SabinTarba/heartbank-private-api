package com.sabintarba.heartbank.privateapi.core.audit;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity class for audit parameters table.
 * 
 * @author Sabin Tarba, sabintarba01@gmail.com
 */
@Entity
@Table(name = "hb_audit_log_param")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuditLogParams {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hb_audit_log_param_id_s")
    @SequenceGenerator(
        name = "hb_audit_log_param_id_s",          
        sequenceName = "hb_audit_log_param_id_s",
        allocationSize = 1
    )
    @Column(name = "audit_log_param_id")
    @JsonIgnore
    private Long id;

    @Column(name = "audit_log_id")
    @JsonIgnore
    private Long auditLogId;
    
    @Column(name = "timestamp_created", nullable = false)
    private LocalDateTime timestampCreated;
    
    @Column(name = "param_key", nullable = false)
    private String key;

    @Column(name = "param_value", nullable = false)
    private String value;
}
