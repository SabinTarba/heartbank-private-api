package com.sabintarba.heartbank.privateapi.core.exception;

/**
 * Unauthorized exception.
 *
 * @author Sabin Tarba, sabintarba01@gmail.com
 */
public class UnauthorizedException extends RuntimeException {
     public UnauthorizedException() {
          super("Unauthorized");
     }
}
