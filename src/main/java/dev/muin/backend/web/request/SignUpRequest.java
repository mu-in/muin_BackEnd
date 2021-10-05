package dev.muin.backend.web.request;

import dev.muin.backend.domain.User.User;
import dev.muin.backend.oauth2.AuthProvider;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class SignUpRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    public SignUpRequest(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User toEntity(AuthProvider authProvider, PasswordEncoder passwordEncoder){
        return User.localBuilder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .provider(authProvider)
                .build();
    }
}