package com.example.todomate.service.UserService;

import com.example.todomate.entity.User;
import com.example.todomate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserSecurityService implements UserService{

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Override
    public Long join(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(userRepository.existsByEmail(user.getEmail())){
            throw new IllegalStateException("이미 존재하는 이메일입니다");
        }
        return userRepository.save(user).getId();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
