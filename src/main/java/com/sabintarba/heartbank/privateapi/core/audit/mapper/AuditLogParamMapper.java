package com.sabintarba.heartbank.privateapi.core.audit.mapper;

import org.springframework.stereotype.Component;

import com.sabintarba.heartbank.privateapi.core.audit.dto.response.AuditLogParamResponse;
import com.sabintarba.heartbank.privateapi.core.audit.entity.AuditLogParamEntity;

@Component
public class AuditLogParamMapper {

    public static AuditLogParamResponse toResponse(AuditLogParamEntity entity) {
        if (entity == null) return null;

        return new AuditLogParamResponse(
            entity.getTimestampCreated(),
            entity.getKey(),
            entity.getValue()
        );
    }
}