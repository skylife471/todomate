package com.example.todomate.service.LoginService;

import com.example.todomate.entity.User;
import com.example.todomate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;

    @Override
    public Optional<User> login(String email, String password) {
        Optional<User> findUser = userRepository.findByEmail(email);
        return findUser.filter((user -> user.getPassword().equals(password)));
    }
}
