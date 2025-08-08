package com.sabintarba.heartbank.privateapi.core.audit.service;

public record AuditCleanupResult(Integer deletedLogsCount, Integer deletedLogsParamCount) { }