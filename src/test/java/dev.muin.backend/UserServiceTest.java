package dev.muin.backend;

import dev.muin.backend.service.UserService;
import dev.muin.backend.web.request.LoginRequest;
import dev.muin.backend.web.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @DisplayName("QR 입장 제한시간 내(27초) 성공")
    @Test
    public void QRauthenticateSuccess() throws Exception {
        final String idToken = "aaaaaaaaaaabbbbbbbbbbbbcccccccccccc";
        final String email = "kim@email.com";
        final String name = "kim";
        LoginRequest loginRequest = new LoginRequest(idToken, email, name);

        // login
        LoginResponse loginResponse = userService.saveOrUpdate(loginRequest);

        // QR
        final String uuid = loginResponse.getUuid();
        long now = new Date().getTime()+27000l;
        final String seed = now+":"+uuid;
        log.info("seed: " + seed);


        boolean res = userService.QRauthentication(seed);
        assertThat(res).isEqualTo(true);
    }

    @DisplayName("QR 입장 제한시간 오버하여(31초) 실패")
    @Test
    public void QRauthenticateFail() throws Exception {
        final String idToken = "aaaaaaaaaaabbbbbbbbbbbbcccccccccccc";
        final String email = "kim@email.com";
        final String name = "kim";
        LoginRequest loginRequest = new LoginRequest(idToken, email, name);

        // login
        LoginResponse loginResponse = userService.saveOrUpdate(loginRequest);

        // QR
        final String uuid = loginResponse.getUuid();
        long now = new Date().getTime();//+25000l;
        final String seed = now+":"+uuid;
        log.info("seed: " + seed);

        boolean res = userService.QRauthentication(seed);
        assertThat(res).isEqualTo(true);
    }
}
