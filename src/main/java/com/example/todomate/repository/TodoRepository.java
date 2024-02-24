package com.example.todomate.repository;

import com.example.todomate.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("select t from Todo t where t.goal.id = :goalId and t.date = :date order by t.createdDateTime")
    List<Todo> findJPQLByGoalIdAndDate(@Param("goalId") Long goalId, @Param("date")LocalDate date);
}
