package com.sabintarba.heartbank.privateapi.core.audit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity class for audit table.
 * 
 * @author Sabin Tarba, sabintarba01@gmail.com
 */
@Entity
@Table(name = "hb_audit_log")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuditLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hb_audit_log_id_s")
    @SequenceGenerator(
        name = "hb_audit_log_id_s",          
        sequenceName = "hb_audit_log_id_s",
        allocationSize = 1
    )
    @Column(name = "audit_log_id")
    private Long id;
    
    @Column(name = "audit_type", nullable = false)
    private String auditType;
    
    @Column(name = "audit_action", nullable = false)
    private String auditAction;

    @Column(name = "timestamp_created", nullable = false)
    private LocalDateTime timestampCreated;

    @OneToMany(mappedBy = "auditLog", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AuditLogParam> params = new ArrayList<>();
}