package dev.muin.backend.config.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/**
 * JWT Filter에서 사용되는 Util
 *
 * @see JwtAuthenticationFilter
 */
@Slf4j
@Component
public class JwtTokenProvider {

    private String secretKey = "niumkimleelimkimmuin";
    private long tokenValidTime = 30 * 60 * 1000L;
    private UserDetailsService userDetailsService;
    private final String JWT_PREFIX = "Bearer ";

    @Autowired
    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // 정보를 암호화하고 signature 적용
    public String createToken(String email) {
        Date now = new Date();
        return Jwts.builder()
                //header
                .setSubject("userInfo") //token 제목
                //payload
                .claim("email", email)
                .setIssuedAt(now)   //토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) //만료 시간
                //signature
                .signWith(SignatureAlgorithm.HS256, secretKey)  //사용할 암호화 알고리즘, signature에 들어갈 secret 값 세팅
                .compact();
    }

    // JWT에서 인증 정보 조회
    public Authentication getAuthentication(String token) throws UsernameNotFoundException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출 (디코딩)
    public String getPk(String token) {
        String res = (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("email");
        log.info("jwt 내부의 사용자 식별자: " + res);
        return res;
    }

    /**
     * Request의 Header에서 token 값을 가져온다.
     *
     * @param request
     * @return jwt
     * @throws NullPointerException HttpHeader에 AUTHORIZATION 키 없으면 발생
     */
    public String resolveToken(HttpServletRequest request) throws NullPointerException {
        String res = null;
        try {
            res = request.getHeader(HttpHeaders.AUTHORIZATION).substring(JWT_PREFIX.length());
            log.info("jwt: " + res);
        } catch (NullPointerException e) {
            throw new NullPointerException("Header for Jwt is not exist or valid");
        }
        return res;
    }

    /**
     * UnsupportedJwtException – if the claimsJws argument does not represent an Claims JWS
     * MalformedJwtException – if the claimsJws string is not a valid JWS
     * SignatureException – if the claimsJws JWS signature validation fails
     * ExpiredJwtException – if the specified JWT is a Claims JWT and the Claims has an expiration time before the time this method is invoked.
     * IllegalArgumentException – if the claimsJws string is null or empty or only whitespace
     */
    public boolean validateToken(String jwtToken) throws RuntimeException {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return true;
        } catch (ExpiredJwtException e) { //jwt가 만료됨
            throw new JwtValidationException("JWT is expired", e, HttpStatus.REQUEST_TIMEOUT);
        } catch (UnsupportedJwtException | MalformedJwtException| IllegalArgumentException e) { //Claims에 담았던 인자의 키밸류들이 아님
            throw new JwtValidationException("JWT's type or value is invalid", e, HttpStatus.UNAUTHORIZED);
        } catch (SignatureException e) { //  signature이 유효하지 않음
            throw new JwtValidationException("JWT's signature is not invalid", e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
