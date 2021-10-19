package dev.muin.backend.config.jwt;

import dev.muin.backend.domain.User.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private String secretKey = "niumkimleelimkimmuin";
    private long tokenValidTime = 30 * 60 * 1000L;
    private UserDetailsService userDetailsService;

    @Autowired
    public JwtTokenProvider(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // 정보를 암호화하고 signature 적용
    public String createToken(String email) {
        Claims claims = Jwts.claims(); // JWT payload에 저장되는 정보단위
        Date now = new Date();
        return Jwts.builder()
                //header
//                .setHeaderParam("typ", "JWT") //token 타입
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
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출 (디코딩)
    public String getPk(String token) {
        String res = (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("email");
        log.info("loadUserByUsername 에 들어감: "+res);
        return res;
    }

    // Request의 Header에서 token 값을 가져온다.
    public String resolveToken(HttpServletRequest request) {
        String res = request.getHeader(HttpHeaders.AUTHORIZATION).substring("Bearer ".length());
        log.info("resolveToken: " + res);
        return res;
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
