package com.sabintarba.heartbank.privateapi.core.audit;

import lombok.Getter;

/**
 * Public enum for representing available audit types.
 *
 * @author Sabin Tarba, sabintarba01@gmail.com
 */
@Getter
public enum AuditType {
    /**
     * Used for account operations.
     */
    ACCOUNT("ACCOUNT"),
    CLEANUP("CLEANUP");

    private final String type;

    /**
     * Constructor with audit type.
     * @param type audit type.
     */
    AuditType(final String type) {
        this.type = type;
    }
}
