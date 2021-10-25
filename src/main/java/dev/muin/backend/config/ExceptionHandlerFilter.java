package dev.muin.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.muin.backend.config.jwt.JwtValidationException;
import dev.muin.backend.web.exceptionHandler.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT, Security관련 Filter에서 발생한 에러를 핸들링합니다.
 * @see dev.muin.backend.config.jwt.JwtAuthenticationFilter
 */
@Slf4j
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request,response);
        } catch (UsernameNotFoundException e){
            e.printStackTrace();
            setErrorResponse(HttpStatus.BAD_REQUEST,response,e);
        } catch(NullPointerException e){
            e.printStackTrace();
            setErrorResponse(HttpStatus.UNAUTHORIZED,response, e);
        } catch(JwtValidationException e) {
            e.printStackTrace();
            setErrorResponse(e.getHttpStatus(), response, e);
        }
        catch (RuntimeException e){
            e.printStackTrace();
            setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,response,e);
        }
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response,Throwable ex){
        response.setStatus(status.value());
        response.setContentType("application/json");
        ErrorResponse errorResponse = new ErrorResponse(status, ex.getMessage());
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), errorResponse);
        }catch (IOException e){
            log.warn("setErrorResponse occurred exception");
            e.printStackTrace();
        }
    }
}
