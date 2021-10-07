package dev.muin.backend.web;

import dev.muin.backend.config.auth.JwtTokenProvider;
import dev.muin.backend.domain.User.Role;
import dev.muin.backend.domain.User.User;
import dev.muin.backend.domain.User.UserRepository;
import dev.muin.backend.web.request.LoginRequest;
import dev.muin.backend.web.request.JoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    //회원가입
    @PostMapping("/join")
    public Short join(JoinRequest joinRequest) {
        return userRepository.save(User.builder()
                .email(joinRequest.getEmail())
                .password(passwordEncoder.encode(joinRequest.getPassword()))
                .role(Role.USER)
                .build()).getId();
    }

    // 로그인
    @PostMapping("/login")
    public String login(LoginRequest loginRequest) {
        User member = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new IllegalArgumentException("가입되지 않은 e-mail 입니다."));
        if(!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRole());
    }
}
