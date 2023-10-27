package com.ownsprojects.ecomerce.web.controller.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * A controller advice class for handling exceptions in the application.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Handles exceptions of type RuntimeException and returns a JSON response.
     *
     * @param ex      The exception that occurred.
     * @param request The current web request.
     * @return A ResponseEntity with a JSON response representing the error.
     */
    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request) {
        // Create an object to represent the error response
        ErrorResponse errorResponse = new ErrorResponse("Bad Request", ex.getMessage());

        // Use ResponseEntity to return the JSON response
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions of type ExpiredJwtException and returns a JSON response.
     *
     * @param ex      The ExpiredJwtException that occurred.
     * @param request The current web request.
     * @return A ResponseEntity with a JSON response indicating an expired token.
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("Expired Token", "The provided token has expired");
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles exceptions of type MalformedJwtException and returns a JSON response.
     *
     * @param ex      The MalformedJwtException that occurred.
     * @param request The current web request.
     * @return A ResponseEntity with a JSON response indicating a malformed token.
     */
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Object> handleMalformedJwtException(MalformedJwtException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("Malformed Token", "The provided token is not well-formed");
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
}