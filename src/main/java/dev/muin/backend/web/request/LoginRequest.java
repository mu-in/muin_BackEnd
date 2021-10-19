package dev.muin.backend.web.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginRequest {

    @NotNull
    private String id_token;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String name;
}
