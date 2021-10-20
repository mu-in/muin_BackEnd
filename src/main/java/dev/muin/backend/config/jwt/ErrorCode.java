package dev.muin.backend.config.jwt;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorCode {
    NO_EXIST_TOKEN("JE1", HttpStatus.UNAUTHORIZED, "JWT is not exist"),
    EXPIRED_TOKEN("JE2", HttpStatus.REQUEST_TIMEOUT, "JWT is expired"),
    INVALID_TOKEN("JE3", HttpStatus.UNAUTHORIZED, "JWT is invalid");
    String code;
    HttpStatus status;
    String errorMsg;
    ErrorCode(String code, HttpStatus status, String errorMsg){
        this.code =code;
        this.status = status;
        this.errorMsg = errorMsg;
    }

}
