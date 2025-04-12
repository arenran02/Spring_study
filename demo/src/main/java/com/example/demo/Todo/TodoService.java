package com.example.demo.Todo;

import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public Todo createTodo(Todo todo, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    public List<Todo> getTodosById(Long userId) {
        return todoRepository.findByUserId(userId);
    }

    public Todo updateTodo(Long id, Todo todoDetails) {
        return todoRepository.findById(id).map(todo -> {
            todo.setTodo(todoDetails.getTodo());
            todo.setTitle(todoDetails.getTitle());
            return todoRepository.save(todo);
        }).orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    public void deleteTodo(Long todoId) {
        todoRepository.findById(todoId).ifPresent(todo -> {
            todo.setUser(null);
            todoRepository.delete(todo);
        });
    }
}
