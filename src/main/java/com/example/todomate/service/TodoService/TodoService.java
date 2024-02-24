package com.example.todomate.service.TodoService;

import com.example.todomate.entity.Todo;

import java.time.LocalDate;
import java.util.Optional;

public interface TodoService {
    //저장
    Long save(Todo todo);
    //조회
    Optional<Todo> findById(Long id);
    //기본 수정
    void update(Long id, String description);
    //삭제
    void delete(Long id);
    //상태 변경
    void changeStatus(Long id);
}
