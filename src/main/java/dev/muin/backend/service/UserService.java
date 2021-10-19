package dev.muin.backend.service;

import dev.muin.backend.config.jwt.JwtTokenProvider;
import dev.muin.backend.domain.User.Role;
import dev.muin.backend.domain.User.User;
import dev.muin.backend.domain.User.UserRepository;
import dev.muin.backend.web.request.LoginRequest;
import dev.muin.backend.web.response.LoginResponse;
import dev.muin.backend.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;




    @Transactional(readOnly = true)
    public LoginResponse saveOrUpdate(LoginRequest loginRequest) {
        User member = userRepository.findByEmail(loginRequest.getEmail())
                .orElse(null);//(() -> new IllegalArgumentException("가입되지 않은 e-mail 입니다."));
        if(member == null) member = join(loginRequest);

        String jwt = jwtTokenProvider.createToken(member.getEmail(), member.getUuid(), member.getRole());

        return new LoginResponse(jwt, member.getUuid(), member.getRole());
    }

    @Transactional(readOnly = true)
    public UserResponse myInfo(String uuid){
        User member = userRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));
        return UserResponse.of(member);
    }

    private User join(LoginRequest loginRequest) throws IllegalArgumentException{
        User user = User.builder()
                .uuid(loginRequest.getUuid())
                .email(loginRequest.getEmail())
                .name(loginRequest.getName())
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }
}
