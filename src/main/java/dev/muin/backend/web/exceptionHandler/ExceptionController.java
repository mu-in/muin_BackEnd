package dev.muin.backend.web.exceptionHandler;

import dev.muin.backend.config.jwt.JwtValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ErrorResponse errorHandler(RuntimeException e) {
        log.warn(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
        return errorResponse;
    }

//    @ExceptionHandler(value = {UsernameNotFoundException.class})
//    public ErrorResponse usernotfoundHandler(UsernameNotFoundException e) {
//        log.warn(e.getMessage());
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//        return errorResponse;
//    }
}
