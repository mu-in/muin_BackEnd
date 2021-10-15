package dev.muin.backend.web;

import dev.muin.backend.config.otp.CryptoType;
import dev.muin.backend.config.otp.TOTP;
import dev.muin.backend.service.UserService;
import dev.muin.backend.web.request.JoinRequest;
import dev.muin.backend.web.request.LoginRequest;
import dev.muin.backend.web.response.LoginResponse;
import dev.muin.backend.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * This request "url" is used by filter
     *
     * @see dev.muin.backend.config.jwt.JwtAuthenticationFilter
     */
    @PostMapping("/join")
    public ResponseEntity<Short> join(@RequestBody JoinRequest joinRequest) throws Exception {
        log.info(joinRequest.toString());
        short userId = userService.join(joinRequest);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    /**
     * This request "url" is used by filter
     *
     * @see dev.muin.backend.config.jwt.JwtAuthenticationFilter
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        log.info(loginRequest.toString());
        LoginResponse loginResponse = userService.login(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserResponse> myInfo(@PathVariable String uuid) {
        UserResponse userResponse = userService.myInfo(uuid);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    /**
     * @param uuid
     * @return 8-digit Time-Based OTP
     */
    @GetMapping("/totp/{uuid}")
    public ResponseEntity<String> generateTOTP(@PathVariable String uuid) throws Exception {

        //현재 시간, 0L(초깃값), 10L(10초까지 TOTP 키 유지 시간)
        Calendar time = Calendar.getInstance();

        // steps: 16자리(0000000009BD76C4)
        String steps = TOTP.calcSteps(time.getTimeInMillis() / 1000, 0L, 10L);

        //seed(시드값), steps(위에서 계산한 값), TOTP 출력 자리수, 암호화 방식 --> 이대로면 8자리 TOTP값이 반환됩니다.
        String res = TOTP.generateTOTP(uuid, steps, "8", CryptoType.HmacSHA512);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
