package dev.muin.backend.web;

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


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * This request url is used by filter
     * @see dev.muin.backend.config.auth.JwtAuthenticationFilter
     */
    @PostMapping("/join")
    public ResponseEntity<Short> join(@RequestBody JoinRequest joinRequest) throws Exception {
        log.info(joinRequest.toString());
        short userId = userService.join(joinRequest);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    /**
     * This request url is used by filter
     * @see dev.muin.backend.config.auth.JwtAuthenticationFilter
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        log.info(loginRequest.toString());
        LoginResponse loginResponse =  userService.login(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserResponse> myinfo(@PathVariable String uuid) {
        UserResponse userResponse = userService.myInfo(uuid);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
