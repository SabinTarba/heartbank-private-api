package com.sabintarba.heartbank.privateapi.core.audit;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for audit module.
 * 
 * @author Sabin Tarba, sabintarba01@gmail.com
 */
public interface AuditLogRepository extends JpaRepository<AuditLog, Long>{
    
}
