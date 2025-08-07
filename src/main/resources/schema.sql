/*
    Database: Oracle
    *********************************************************************
    Version: 1.0.0 
    Last updated: 07.08.2025
    Updated by: Sabin Tarba (sabintarba01@gmail.com)
    Last change description: Init HB_AUDIT_LOG & HB_AUDIG_LOG_PARAM
    *********************************************************************
    Notes:
    - "schema.sql" represents the way of initialising the database schema for Oracle Databases
    - all schema objects are prefixed with "hb_" (HeartBank) for creating a namespace at oracle user/schema level
    - the recomandation is to have a dedicated Oracle schema for this, even if the objects supports namespacing at name level
*/

-- Table for audit.
CREATE TABLE hb_audit_log (
    audit_log_id NUMBER(19) NOT NULL,
    audit_type VARCHAR2(30) NOT NULL,
    audit_action VARCHAR2(30) NOT NULL,
    timestamp_created TIMESTAMP(6) DEFAULT SYSTIMESTAMP NOT NULL,

    CONSTRAINT hb_audit_log_pk PRIMARY KEY (audit_log_id),
    CONSTRAINT hb_audit_log_audit_type_ck CHECK (audit_type in ('ACCOUNT', 'CLEANUP'))
);

-- Sequence for hb_audit_log.audit_log_id.
CREATE SEQUENCE hb_audit_log_id_s NOCACHE;

-- Table for audit parameters.
CREATE TABLE hb_audit_log_param (
    audit_log_param_id NUMBER(19) NOT NULL,
    audit_log_id NUMBER(19) NOT NULL,
    timestamp_created TIMESTAMP(6) DEFAULT SYSTIMESTAMP NOT NULL,
    param_key VARCHAR2(100) NOT NULL,
    param_value VARCHAR2(250) NOT NULL,
    
    CONSTRAINT hb_audit_log_param_pk PRIMARY KEY (audit_log_param_id)
);

-- Sequence for hb_audit_log_param.audit_log_parma_id.
CREATE SEQUENCE hb_audit_log_param_id_s NOCACHE;

-- Index for hb_audit_log_param.audit_log_id.
CREATE INDEX hb_audit_log_param_auditlgid_i ON hb_audit_log_param (audit_log_id);