package dev.muin.backend.web.request;

import lombok.*;

import javax.validation.constraints.Email;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginRequest {
    @NonNull
    @Email
    private String email;

    @NonNull
    private String password;
}
