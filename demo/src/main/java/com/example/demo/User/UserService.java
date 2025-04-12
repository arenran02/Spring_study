package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // 리포지토리에 의존성 주입
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE: 사용자 생성
    public User createUser(User user) {
        return userRepository.save(user);
        // EntityManager의 persist -> flush 실행을 통해 수행됨
        // flush로 인해서 성능이 정해짐
    }

    // READ: 모든 사용자 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // READ: 특정 사용자 조회
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // UPDATE: 사용자 정보 수정
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setGender(userDetails.getGender());
            user.setAge(userDetails.getAge());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // DELETE: 사용자 삭제
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}
