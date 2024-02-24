package com.example.todomate.service.TodoService;

import com.example.todomate.entity.Goal;
import com.example.todomate.entity.Todo;
import com.example.todomate.repository.GoalRepository;
import com.example.todomate.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;
    private final GoalRepository goalRepository;

    @Override
    @Transactional
    public Long save(Todo todo) {
        return todoRepository.save(todo).getId();
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    //업데이트
    @Override
    @Transactional
    public void update(Long id, String description) {
        Optional<Todo> toDo = todoRepository.findById(id);
        toDo.ifPresent(t -> t.setDescription(description));
    }

    //삭제
    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Todo> findId = todoRepository.findById(id);
        if(findId.isPresent()){
            Todo todo = findId.get();
            Goal goal = todo.getGoal();
            goal.getTodo().remove(todo);
            todoRepository.deleteById(id);
        }
    }

    //상태변경
    @Override
    @Transactional
    public void changeStatus(Long id) {
        Optional<Todo> toDo = todoRepository.findById(id);
        toDo.ifPresent(t->t.changeStatus());
    }

    //날짜 변경
    @Transactional
    public void changeDate(Long id, LocalDate date){
        Optional<Todo> toDo = todoRepository.findById(id);
        toDo.ifPresent(t-> t.setDate(date));
    }

    //목표와 날짜와 일치 하는 할 일 출력
    public List<Todo> findByGoalAndDate(Long id, LocalDate date){
        return todoRepository.findJPQLByGoalIdAndDate(id, date);
    }
}
