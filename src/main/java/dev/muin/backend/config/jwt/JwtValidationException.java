package dev.muin.backend.config.jwt;

import io.jsonwebtoken.JwtException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

/**
 * This occurs when jwt included in Http request header is not valid
 */
@Getter
public class JwtValidationException extends JwtException {

    private HttpStatus httpStatus;

    public JwtValidationException(String msg) {
        super(msg);
    }

    public JwtValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtValidationException(String msg, Throwable cause, HttpStatus httpStatus) {
        super(msg, cause);
        this.httpStatus = httpStatus;
    }
}
