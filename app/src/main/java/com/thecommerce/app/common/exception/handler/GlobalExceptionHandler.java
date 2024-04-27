package com.thecommerce.app.common.exception.handler;

import com.thecommerce.app.common.exception.ValidationException;
import com.thecommerce.app.common.util.Response;
import com.thecommerce.app.domain.user.exception.AlreadyExistNicknameException;
import com.thecommerce.app.domain.user.exception.AlreadyExistUserIdException;
import com.thecommerce.app.domain.user.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400
    @ExceptionHandler({
            ValidationException.class,
            AlreadyExistUserIdException.class,
            AlreadyExistNicknameException.class,
            IllegalArgumentException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<Response> badRequest(final RuntimeException e) {
        log.warn("bad request error.", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Response.error(e.getMessage()));
    }


    // 404
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Response> notFound(final NoHandlerFoundException e) {
        log.warn("Not found error.", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.error(e.getMessage()));
    }

    // 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> internalServerError(final Exception e) {
        log.warn("Internal server error.", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(e.getMessage()));
    }
}
