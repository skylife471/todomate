package com.example.todomate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;
    //이메일로 로그인을 함
    private String email;

    private String password; // Bcrypt로 해싱

    private String description;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Goal> goal = new ArrayList<>();
}
