package dev.muin.backend.web.request;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}
