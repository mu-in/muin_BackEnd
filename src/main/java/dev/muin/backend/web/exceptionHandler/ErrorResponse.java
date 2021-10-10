package dev.muin.backend.web.exceptionHandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private int status;
    private String errorMsg;

    public ErrorResponse( HttpStatus status,String errorMsg){
        this.status = status.value();
        this.errorMsg = errorMsg;
    }
}
