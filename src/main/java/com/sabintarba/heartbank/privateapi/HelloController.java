package com.sabintarba.heartbank.privateapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabintarba.heartbank.privateapi.core.audit.AuditLogService;
import com.sabintarba.heartbank.privateapi.core.audit.AuditType;
import com.sabintarba.heartbank.privateapi.core.exception.ApiException;
import com.sabintarba.heartbank.privateapi.core.exception.ErrorCode;

@RestController
public class HelloController {
    
    @Autowired 
    AuditLogService auditLogService;

    @GetMapping("/home")
    private String home(){
        auditLogService.audit(AuditType.ACCOUNT, "HOME", null);
        throw new ApiException(ErrorCode.HELLO_NOT_FOUND, "Hello not found ..");
    }
}
