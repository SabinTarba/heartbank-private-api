package com.sabintarba.heartbank.privateapi.core.audit.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class AuditLogParamEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hb_audit_log_param_id_s")
    @SequenceGenerator(
        name = "hb_audit_log_param_id_s",          
        sequenceName = "hb_audit_log_param_id_s",
        allocationSize = 1
    )
    @Column(name = "audit_log_param_id")
    private Long id;
    
    @Column(name = "timestamp_created", nullable = false)
    private LocalDateTime timestampCreated;
    
    @Column(name = "param_key", nullable = false)
    private String key;

    @Column(name = "param_value", nullable = false)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "audit_log_id", nullable = false)
    private AuditLogEntity auditLog;
}
