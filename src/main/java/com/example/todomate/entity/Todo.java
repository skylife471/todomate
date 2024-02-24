package com.example.todomate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    private LocalDateTime createdDateTime;

    private String description;

    private String memo;

    private LocalDate date;

    private Boolean isDone;

    private byte[] imageData;

    private LocalDateTime alarmDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private Routine routine;

    public void changeStatus(){
        this.isDone = !this.isDone;
    }
    //==연관관계 편의 메서드==//
    public void setGoal(Goal goal){
        this.goal = goal;
        goal.getTodo().add(this);
    }
}
