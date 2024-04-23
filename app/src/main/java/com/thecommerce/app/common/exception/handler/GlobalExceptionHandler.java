package com.thecommerce.app.common.exception.handler;

import com.thecommerce.app.common.exception.ValidationException;
import com.thecommerce.app.common.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Response> validationError(final ValidationException e) {
        log.warn("Validation error.", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Response.error(e.getMessage()));
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Response> notFound(final NoHandlerFoundException e) {
        log.warn("Not found error.", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.error(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> internalServerError(final Exception e) {
        log.warn("Internal server error.", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(e.getMessage()));
    }
}
