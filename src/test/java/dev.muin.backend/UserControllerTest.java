package dev.muin.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.muin.backend.config.auth.JwtTokenProvider;
import dev.muin.backend.service.UserService;
import dev.muin.backend.web.UserController;
import dev.muin.backend.web.request.JoinRequest;
import dev.muin.backend.web.request.LoginRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    UserService userService;
    @MockBean
    JwtTokenProvider jwtTokenProvider;
    @MockBean
    AuthenticationManager authenticationManager;

    ObjectMapper objectMapper = new ObjectMapper();

    @WithAnonymousUser
    @DisplayName("회원가입 성공")
    @Test
    public void join() throws Exception {
        JoinRequest joinRequest = new JoinRequest("kim@gmail.com", "kim", "1111");

        given(userService.join(joinRequest)).willReturn((short) 1);

        mvc.perform(post("/user/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinRequest)))
                .andExpect(status().isOk());
    }

    @DisplayName("로그인 성공")
    @Test
    public void login() throws Exception{
        LoginRequest loginRequest = new LoginRequest("kim@gmail.com", "1111");

        given(userService.login(loginRequest)).willReturn("sometoken");

        mvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk());
    }

}
