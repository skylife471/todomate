package com.example.todomate.service.UserService;

import com.example.todomate.entity.User;
import com.example.todomate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    /**
     * 회원가입
     */
    @Transactional
    @Override
    public Long join(User user){
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    /**
     * 회원 조회
     */
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * 이메일로 회원 조회
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * 중복검증
     */
    private void validateDuplicateUser(User user) {
        Optional<User> data = userRepository.findByEmail(user.getEmail());
        if(data.isPresent()){
            throw new IllegalStateException("이미 가입된 이메일 입니다");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<User> findUsers(){
        return userRepository.findAll();
    }
}
