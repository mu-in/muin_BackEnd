package dev.muin.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.muin.backend.config.jwt.JwtTokenProvider;
import dev.muin.backend.domain.User.Role;
import dev.muin.backend.service.UserService;
import dev.muin.backend.web.UserController;
import dev.muin.backend.web.request.LoginRequest;
import dev.muin.backend.web.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
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

    @DisplayName("로그인 및 회원가입 성공")
    @Test
    public void login() throws Exception{
        LoginRequest loginRequest = new LoginRequest("idtokenexample","kimgmailcom","kim");
        LoginResponse loginResponse = new LoginResponse("jwtexample", "uuidexample", Role.USER);

        given(userService.saveOrUpdate(loginRequest)).willReturn(loginResponse);

        mvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk());
    }

}
