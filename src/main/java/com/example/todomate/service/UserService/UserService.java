package com.example.todomate.service.UserService;

import com.example.todomate.entity.User;

import java.util.Optional;

public interface UserService {
    Long join(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
