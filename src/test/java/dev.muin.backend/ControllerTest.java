package dev.muin.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.muin.backend.web.request.LoginRequest;
import dev.muin.backend.web.request.SignUpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/signup"))
                .andExpect(MockMvcResultMatchers.status().is(401));
    }

    @Test
    public void test2() throws Exception {
        SignUpRequest signUpRequest = new SignUpRequest("kim", "kim@mail", "1111");
        String content = objectMapper.writeValueAsString(signUpRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/signup").content(content))
                .andExpect(MockMvcResultMatchers.status().is(401));
    }

    @DisplayName("기존 회원 로그인 요청")
    @Test
    public void test3() throws Exception {
        LoginRequest loginRequest = new LoginRequest("kim@mail", "1111");
        LoginRequest loginRequest2 = new LoginRequest("kim2@mail", "2222");
        String content = objectMapper.writeValueAsString(loginRequest);
        String content2 = objectMapper.writeValueAsString(loginRequest2);

        mockMvc.perform(MockMvcRequestBuilders.post("/signup").content(content))
                .andExpect(MockMvcResultMatchers.status().is(401));
        mockMvc.perform(MockMvcRequestBuilders.post("/signup").content(content2))
                .andExpect(MockMvcResultMatchers.status().is(401));
    }
}
