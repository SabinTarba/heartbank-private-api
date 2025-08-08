package com.sabintarba.heartbank.privateapi.core.audit.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sabintarba.heartbank.privateapi.core.audit.dto.response.AuditLogParamResponse;
import com.sabintarba.heartbank.privateapi.core.audit.dto.response.AuditLogResponse;
import com.sabintarba.heartbank.privateapi.core.audit.entity.AuditLogEntity;

@Component
public class AuditLogMapper {

    public static AuditLogResponse toResponse(AuditLogEntity entity) {
        if (entity == null) return null;

        List<AuditLogParamResponse> params = entity
                                        .getParams()
                                        .stream()
                                        .map(AuditLogParamMapper::toResponse)
                                        .collect(Collectors.toList());

        return new AuditLogResponse(
            entity.getId(),
            entity.getAuditType(),
            entity.getAuditAction(),
            entity.getTimestampCreated(),
            params
        );
    }
}