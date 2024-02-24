package com.example.todomate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Routine {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long id;

    private String title;

    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    private Repetition repetition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    @OneToOne(mappedBy = "routine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Todo todo;

    //==연관관계 편의 메서드==//

    public void setGoal(Goal goal){
        this.goal = goal;
        goal.getRoutine().add(this);
    }

    public void setTodo(Todo todo){
        this.todo = todo;
        todo.setRoutine(this);
    }
}
