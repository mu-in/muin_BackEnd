package dev.muin.backend.web.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {IllegalArgumentException.class, UsernameNotFoundException.class})
    public ErrorResponse errorHandler(RuntimeException e) {
        e.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ErrorResponse npeHandler(NullPointerException e) {
        e.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
        return errorResponse;
    }
}
