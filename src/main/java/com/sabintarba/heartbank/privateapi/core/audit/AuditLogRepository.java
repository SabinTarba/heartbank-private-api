package com.sabintarba.heartbank.privateapi.core.audit;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for audit module.
 * 
 * @author Sabin Tarba, sabintarba01@gmail.com
 */
public interface AuditLogRepository extends JpaRepository<AuditLog, Long>{

    /**
     * Delete old audit logs.
     * @param cutoffDateTime current timestamp - audit.retention.days parameter
     * @return number of deleted rows
     */
    @Modifying
    @Query("DELETE FROM AuditLog a WHERE a.timestampCreated < :cutoffDateTime")
    Integer deleteOldLogs(@Param("cutoffDateTime") LocalDateTime cutoffDateTime);

    /**
     * Delete old audit logs parameters.
     * @param cutoffDateTime current timestamp - audit.retention.days parameter
     * @return number of deleted rows
     */
    @Modifying
    @Query("DELETE FROM AuditLogParam a WHERE a.timestampCreated < :cutoffDateTime")
    Integer deleteOldLogsParameters(@Param("cutoffDateTime") LocalDateTime cutoffDateTime);
}
