package dev.muin.backend.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.muin.backend.web.exceptionHandler.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter에서 예외가 발생하면(x) 유효한 자격증명을 가지지않는 사용자가 접근하면(o)
 * 여기서 처리하여 바로 응답하게 한다.
 * 즉, 권한 설정 문제임. 제한 없는 url에선 작동하지 않는다.
 * @see JwtAuthenticationFilter
 */
@Component
@Slf4j
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private ObjectMapper mapper;

    public AuthEntryPointJwt() {
        mapper = new ObjectMapper();
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String exception = (String) request.getAttribute("exception");

        log.info("====Here is commence====");
        log.warn("log: exception: {} ", exception);

        if (exception.equals(ErrorCode.NO_EXIST_TOKEN.getCode())) {
            setResponse(response, ErrorCode.NO_EXIST_TOKEN);
        }
        if (exception.equals(ErrorCode.EXPIRED_TOKEN.getCode())) {
            setResponse(response, ErrorCode.EXPIRED_TOKEN);
        }
        if (exception.equals(ErrorCode.INVALID_TOKEN.getCode())) {
            setResponse(response, ErrorCode.INVALID_TOKEN);
        }
    }

    /**
     * 한글 출력을 위해 getWriter() 사용
     */
    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getStatus(), errorCode.getErrorMsg());
        mapper.writeValue(response.getOutputStream(), errorResponse);
//        response.getWriter().println("{ \"status\" : \"" + errorCode.getStatus().value()+
//                "\", \"message\" : \"" + errorCode.getErrorMsg()
//                + "\"}");
    }
}
