package com.sabintarba.heartbank.privateapi.core.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Public enum for representing error codes for mapping easier.
 * Error code is returned as "errorCode" parameter in API response.
 *
 * @author Sabin Tarba, sabintarba01@gmail.com
 */
@Getter
public enum ErrorCode {
    /**
     * Internal server error and uncatched exceptions.
     */
    INTERNAL_SERVER_ERROR("ERR000", HttpStatus.INTERNAL_SERVER_ERROR),

    /**
     * Resource not found.
     */
    NOT_FOUND("ERR001", HttpStatus.NOT_FOUND),

    /**
     * Unauthorized access.
     */
    UNAUTHORIZED("ERR002", HttpStatus.UNAUTHORIZED),

    /**
     * Generic error code triggered if validation fails for an API request argument/parameter.
     */
    ARGUMENT_VALIDATION_ERROR("ERR003", HttpStatus.BAD_REQUEST),

    /**
     * Hello ID provided for querying not found.
     */
    HELLO_NOT_FOUND("ERR004", HttpStatus.NOT_FOUND);

    private final String code;
    private final HttpStatus httpStatus;

    /**
     * Constructor with code.
     * @param code error code.
     * @param httpStatus HttpStatus object
     */
    ErrorCode(final String code, final HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
