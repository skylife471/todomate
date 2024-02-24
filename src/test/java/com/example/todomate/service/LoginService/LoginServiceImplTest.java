package com.example.todomate.service.LoginService;

import com.example.todomate.entity.User;
import com.example.todomate.repository.UserRepository;
import com.example.todomate.service.UserService.UserSecurityService;
import com.example.todomate.service.UserService.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class LoginServiceImplTest {

    @Autowired
    private UserSecurityService userSecurityService;
    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void 회원등록(){
        User user = new User();
        user.setName("민성");
        user.setPassword("alstjd45");
        user.setEmail("skylife471@naver.com");
        userSecurityService.join(user);
    }
    @Test
    void 로그인테스트(){
        User user = userService.findByEmail("skylife471@naver.com").get();
        bCryptPasswordEncoder.matches("alstjd45", user.getPassword());
    }
}