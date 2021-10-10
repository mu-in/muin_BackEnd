package dev.muin.backend.web.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ErrorResponse errorHandler(RuntimeException e) {
        log.error(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
        return errorResponse;
    }

//    @ExceptionHandler(value = {ConstraintViolationException.class})
//    public ErrorResponse validationHandler(ValidationException e) {
//        log.error(e.getMessage());
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//        return errorResponse;
//    }
}
