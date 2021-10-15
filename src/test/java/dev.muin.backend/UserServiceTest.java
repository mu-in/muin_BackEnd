package dev.muin.backend;

import dev.muin.backend.domain.User.UserRepository;
import dev.muin.backend.web.request.JoinRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public void setup(){
        JoinRequest joinRequest = new JoinRequest("uuidexample", "kim@gmail.com", "kim", "1111");
    }

//    @DisplayName("첫 회원가입이면 정보를 저장한다.")
//    @Test
//    public void login() {
//
//    }
//
//    @DisplayName("존재하는 회원이 회원가입하면 정보를 업데이트한다.")
//    @Test
//    public void login2() {
//
//    }
//
//    @DisplayName("비밀번호는 암호화돼서 저장된다.")
//    @Test
//    public void passwordEncrypt(){
//
//    }
}
