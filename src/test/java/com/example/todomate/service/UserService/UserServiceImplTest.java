package com.example.todomate.service.UserService;

import com.example.todomate.entity.User;
import com.example.todomate.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(value = false)
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserSecurityService userSecurityService;

    @Test
    void 비밀번화해싱(){
        User user = new User();
        user.setName("민성");
        user.setPassword("alstjd45");
        user.setEmail("skylife471@naver.com");
        userSecurityService.join(user);
    }
    @Test
    void 회원가입(){
        //given
        User user = new User();
        user.setName("민성");
        user.setPassword("alstjd45");
        user.setEmail("skylife471@naver.com");
        //when
        Long savedId = userService.join(user);
        User savedUser = userRepository.findById(savedId).get();
        //then
        Assertions.assertThat(savedId).isEqualTo(savedUser.getId());
    }

    @Test()
    void 회원가입중복확인(){
        //given
        User user1 = new User();
        user1.setName("민성");
        user1.setEmail("skylife471@naver.com");
        userService.join(user1);

        User user2 = new User();
        user2.setName("민성2");
        user2.setEmail("skylife471@naver.com");
        //given
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, ()->{
            userService.join(user2);
        });
    }
}