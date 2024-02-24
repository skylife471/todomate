package com.example.todomate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Goal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private Long id;

    private String title;

    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    private List<Routine> routine = new ArrayList<>();

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    private List<Todo> todo = new ArrayList<>();

    //==연관관계 편의 메소드==//
    public void setUser(User user){
        this.user = user;
        user.getGoal().add(this);
    }

}
