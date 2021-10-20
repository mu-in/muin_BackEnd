package dev.muin.backend.config.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Jwt를 인증, 관리하는 필터로, 매 요청마다 유효한 토큰인지 검사한다.
 *
 * @see AuthenticationEntryPoint filter의 예외 핸들링
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = ((HttpServletRequest) request).getRequestURI();

        if (!path.equals("/user/login")) {
            // 헤더에서 jwt를 읽어온다.
            String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

            //유효한 토큰인지 확인
            if (token != null && jwtTokenProvider.validateToken(request, token)) {
                // 토큰이 유효하면 토큰에서 유저 정보를 받아온다.
                Authentication authentication = jwtTokenProvider.getAuthentication(request,token);
                // SecurityContext에 Authentication 객체를 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
