package dev.muin.backend.web;

import dev.muin.backend.service.UserService;
import dev.muin.backend.web.request.LoginRequest;
import dev.muin.backend.web.request.AddManagerRoleRequest;
import dev.muin.backend.web.response.LoginResponse;
import dev.muin.backend.web.response.UserResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final String QR_SEPARATOR = ":";

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
     * @param seed {time}:{uuid}
     */
    @GetMapping("/qrcode")
    public ResponseEntity<Boolean> generateTOTP(@RequestParam @NonNull String seed) throws Exception {
        boolean res = false;

        // 서버
        Date now = new Date();
        Long nowMilli = now.getTime();
        Long serverValidTime = nowMilli / QR_VALID_TIME;

        String[] seq = null;
        Long reqTimeMilli;
        try {
            seq = seed.split(QR_SEPARATOR);
            reqTimeMilli = Long.parseLong(seq[0]);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Separator of the value is not exist or invalid");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Format of the value is invalid");
        }
        Long clientValidTime = reqTimeMilli / QR_VALID_TIME;

        if (serverValidTime == clientValidTime) res = true;

        return ResponseEntity.ok(res);
    }

    @PostMapping("/auth/store")
    public ResponseEntity<String> authenticateManager(HttpServletRequest request,  @RequestBody AddManagerRoleRequest addManagerRoleRequest) {
        String res = userService.authenticateManager(request, addManagerRoleRequest);
        return ResponseEntity.ok(res);
    }
}
