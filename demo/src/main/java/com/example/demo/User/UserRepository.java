package com.example.demo.User;

import com.example.demo.Todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { // User라는 객체만 넣을 수 있음을 알려주는 것 <User, Long>
}