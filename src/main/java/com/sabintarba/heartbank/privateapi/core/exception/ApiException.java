package com.sabintarba.heartbank.privateapi.core.exception;

import lombok.Getter;

/**
 * Business API exception handled.
 *
 * @author Sabin Tarba, sabintarba01@gmail.com
 */
@Getter
public class ApiException extends RuntimeException {

    private final ErrorCode errorCode;

    /**
     * Constructor with errorCode and message.
     * @param errorCode ErrorCode
     * @param message Error message.
     */
    public ApiException(final ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
