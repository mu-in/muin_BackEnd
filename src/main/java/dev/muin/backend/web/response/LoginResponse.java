package dev.muin.backend.web.response;

import dev.muin.backend.domain.User.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
    private String jwt;
    private String uuid;
    private Role role;
}
