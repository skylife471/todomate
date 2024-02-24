package com.example.todomate.service.LoginService;

import com.example.todomate.entity.User;

import java.util.Optional;

public interface LoginService {
    Optional<User> login(String email, String password);
}
