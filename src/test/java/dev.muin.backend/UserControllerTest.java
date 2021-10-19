package dev.muin.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.muin.backend.config.jwt.JwtTokenProvider;
import dev.muin.backend.domain.User.Role;
import dev.muin.backend.service.UserService;
import dev.muin.backend.web.UserController;
import dev.muin.backend.web.request.LoginRequest;
import dev.muin.backend.web.response.LoginResponse;
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

//    @DisplayName("회원가입 성공")
//    @Test
//    public void join() throws Exception {
//        JoinRequest joinRequest = new JoinRequest("uuid", "kim@gmail.com","kim", "1111");
//
//        given(userService.join(joinRequest)).willReturn((short) 1);
//
//        mvc.perform(post("/user/join")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(joinRequest)))
//                .andExpect(status().isOk());
//    }

//    @DisplayName("회원가입 email 형식 오류")
//    @Test
//    public void join2() throws Exception {
//        JoinRequest joinRequest = new JoinRequest("uuidexample","kimgmailcom","kim", "1111");
//
//        given(userService.join(joinRequest)).willThrow();
//
//        mvc.perform(post("/user/join")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(joinRequest)))
//                .andExpect(status().isOk());
//    }

    @DisplayName("로그인 성공")
    @Test
    public void login() throws Exception{
        LoginRequest loginRequest = new LoginRequest("uuidexample","kimgmailcom","kim", "1111");
        LoginResponse loginResponse = new LoginResponse("tokenexample", "uuidexample", Role.USER);

        given(userService.saveOrUpdate(loginRequest)).willReturn(loginResponse);

        mvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk());
    }

//    @DisplayName("t-otp 생성")
//    @Test
//    public void tOTPGenerate() throws Exception {
//        String seed = "testseed";
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> res = restTemplate.getForEntity("https://mu-in.herokuapp.com/user/totp/"+seed, String.class);
//        System.out.println(res);
//    }

}
