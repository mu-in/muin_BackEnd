package dev.muin.backend.web;

import dev.muin.backend.service.UserService;
import dev.muin.backend.web.request.LoginRequest;
import dev.muin.backend.web.response.LoginResponse;
import dev.muin.backend.web.response.UserResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.regex.PatternSyntaxException;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final short QR_VALID_TIME = 30; //30sec

    /**
     * This request "url" String is used by filter
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

    /**
     * @param seed {time}:{uuid}
     */
    @GetMapping("/qrcode")
    public ResponseEntity<Boolean> generateTOTP(@RequestParam @NonNull String seed) throws Exception{
        boolean res = false;

        // 서버
        Date now = new Date();
        Long nowMilli = now.getTime();
        Long serverValidTime = nowMilli / QR_VALID_TIME;

        String[] seq = null;
        try {
            seq = seed.split(":");
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Character \":\" is not exist in query string");
        }
        Long reqTimeMilli = Long.parseLong(seq[0]);
        Long clientValidTime = reqTimeMilli / QR_VALID_TIME;

        if (serverValidTime == clientValidTime) res = true;

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
