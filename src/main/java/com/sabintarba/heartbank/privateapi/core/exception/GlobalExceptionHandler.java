package com.sabintarba.heartbank.privateapi.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller advice for catching exceptions and return proper response to the client.
 *
 * @author Sabin Tarba, sabintarba01@gmail.com
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Used to encapsulate the error information in a template error response to to client.
     * @param errorCode error code enum field
     * @param errorMessage error message
     * @param httpStatus http status object
     * @return Map<String, Objects> for JSON formatting
     */
    private Map<String, Object> buildResponseBody(ErrorCode errorCode, String errorMessage, HttpStatus httpStatus){
        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("errorMessage", errorMessage);
        body.put("errorCode", errorCode.getCode());
        body.put("status", httpStatus.value());

        return body;
    }

    /**
     * Generic API business exception handler.
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException ex) {
        logger.warn("API exception: {}, error code: {}", ex.getMessage(), ex.getErrorCode());

        ErrorCode errorCode = ex.getErrorCode();

        Map<String, Object> body = buildResponseBody(errorCode, ex.getMessage(), errorCode.getHttpStatus());

        return new ResponseEntity<>(body, errorCode.getHttpStatus());
    }

    /**
     * Unauthorized access exception handler.
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleNotAuthorizedException(UnauthorizedException ex) {
        logger.warn("API not authorized: {}, error code: {}", ex.getMessage(), ErrorCode.UNAUTHORIZED);

        Map<String, Object> body = buildResponseBody(ErrorCode.UNAUTHORIZED, ex.getMessage(), HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(body, ErrorCode.UNAUTHORIZED.getHttpStatus());
    }

    /**
     * Resource not found exception handler.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex) {
        logger.error("Resource not found exception: ", ex);

        Map<String, Object> body = buildResponseBody(ErrorCode.NOT_FOUND, "Resource not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(body, ErrorCode.NOT_FOUND.getHttpStatus());
    }

    /**
     * Unhandled exception / internal server error exception handler.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternalErrorException(Exception ex) {
        logger.error("Unexpected exception: ", ex);

        Map<String, Object> body = buildResponseBody(ErrorCode.INTERNAL_SERVER_ERROR, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(body, ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus());
    }
}