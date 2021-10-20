package dev.muin.backend.web;

import dev.muin.backend.config.otp.CryptoType;
import dev.muin.backend.config.otp.TOTP;
import dev.muin.backend.service.UserService;
import dev.muin.backend.web.request.LoginRequest;
import dev.muin.backend.web.request.QRRequest;
import dev.muin.backend.web.response.LoginResponse;
import dev.muin.backend.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * This request "url" String is used by filter
     *
     * @see dev.muin.backend.config.jwt.JwtAuthenticationFilter
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        log.info(loginRequest.toString());
        LoginResponse loginResponse = userService.saveOrUpdate(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserResponse> myInfo(@PathVariable String uuid) {
        UserResponse userResponse = userService.myInfo(uuid);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping("/totp")
    public ResponseEntity<String> generateTOTP(@RequestBody QRRequest qrRequest) throws Exception {
        // check validation of requested time
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
