package com.example.todomate.service.TodoService;

import com.example.todomate.entity.Goal;
import com.example.todomate.entity.Todo;
import com.example.todomate.entity.User;
import com.example.todomate.repository.GoalRepository;
import com.example.todomate.repository.TodoRepository;
import com.example.todomate.repository.UserRepository;
import com.example.todomate.service.UserService.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(value = false)
class TodoServiceImplTest {

    @Autowired
    private GoalRepository goalRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoServiceImpl todoService;
    @Autowired
    private UserServiceImpl userService;

    void 할일세팅(){
        User user = new User();
        user.setEmail("skylife471@naver.com");
        user.setName("민성");
        userRepository.save(user);

        Goal goal = new Goal();
        goal.setTitle("운동");
        goal.setUser(user);
        goalRepository.save(goal);

        Goal goal2 = new Goal();
        goal.setTitle("식사");
        goal.setUser(user);
        goalRepository.save(goal);

        //cascade.all때문에 save안해도 저장이되네 나중에 공부해보자
        Todo todo1 = new Todo();
        todo1.setDescription("운동하기");
        todo1.setGoal(goal);
        todo1.setIsDone(true);

        Todo todo2 = new Todo();
        todo2.setDescription("밥먹기");
        todo2.setGoal(goal);
    }
    @Test
    void 할일업데이트() throws Exception {
        //given
        User user = new User();
        user.setEmail("skylife471@naver.com");
        user.setName("민성");
        userService.join(user);

        Goal goal = new Goal();
        goal.setTitle("운동");
        goal.setUser(user);
        goalRepository.save(goal);

        Goal goal2 = new Goal();
        goal.setTitle("식사");
        goal.setUser(user);
        goalRepository.save(goal);

        //cascade.all때문에 save안해도 저장이되네 나중에 공부해보자
        Todo todo1 = new Todo();
        todo1.setDescription("운동하기");
        todo1.setGoal(goal);
        todo1.setIsDone(true);
        todoService.save(todo1);

        Todo todo2 = new Todo();
        todo2.setDescription("밥먹기");
        todo2.setGoal(goal);
        todoService.save(todo2);
        //when
        Todo todo = todoService.findById(todo1.getId()).get();
        todoService.update(todo.getId(), "스쿼트하기");
        //then
        Assertions.assertThat(todo.getDescription()).isEqualTo("스쿼트하기");
    }

    @Test
    @Rollback(value = false)
    void 할일삭제() throws Exception {
        //given
        User user = new User();
        user.setEmail("skylife471@naver.com");
        user.setName("민성");
        userService.join(user);

        Goal goal = new Goal();
        goal.setTitle("운동");
        goal.setUser(user);
        goalRepository.save(goal);

        Goal goal2 = new Goal();
        goal.setTitle("식사");
        goal.setUser(user);
        goalRepository.save(goal);

        //cascade.all때문에 save안해도 저장이되네 나중에 공부해보자
        Todo todo1 = new Todo();
        todo1.setDescription("운동하기");
        todo1.setGoal(goal);
        todo1.setIsDone(true);
        todoService.save(todo1);

        Todo todo2 = new Todo();
        todo2.setDescription("밥먹기");
        todo2.setGoal(goal);
        todoService.save(todo2);

        //when
        todoService.delete(todo2.getId());

        //then
    }

    @Test
    void 목표와날짜일치항목출력() throws Exception {
        //given


        //when


        //then

    }

}