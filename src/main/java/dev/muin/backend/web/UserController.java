package dev.muin.backend.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.muin.backend.service.UserService;
import dev.muin.backend.web.request.AddManagerRoleRequest;
import dev.muin.backend.web.request.LoginRequest;
import dev.muin.backend.web.response.LoginResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


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
        return ResponseEntity.ok(loginResponse);
    }

    /**
     * This request "url" String is used by filter
     *
     * @param seed {time}:{uuid}
     * @see dev.muin.backend.config.jwt.JwtAuthenticationFilter
     */
    @GetMapping("/qrcode")
    public ResponseEntity<Boolean> QRauthentication(@RequestParam @NonNull String seed) throws Exception {
        boolean res = userService.QRauthentication(seed);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/auth/store")
    public ResponseEntity<String> authenticateManager(
            HttpServletRequest request, @RequestBody AddManagerRoleRequest addManagerRoleRequest) throws Exception{
        String res = userService.authenticateManager(request, addManagerRoleRequest);
        return ResponseEntity.ok(res);
    }
}
