package com.example.demo.Todo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/todo")

public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @PostMapping("/{userId}")
    public Todo createTodo(@PathVariable Long userId, @RequestBody Todo todo){
        return todoService.createTodo(todo, userId);
    }
    
    @GetMapping("/{userId}")
    public List<Todo> getTodoByUserId(@PathVariable Long userId){
        return todoService.getTodosById(userId);
    }

    @PutMapping("/{todoId}")
    public Todo updateTodo(@PathVariable Long todoId, @RequestBody Todo todo){
        return todoService.updateTodo(todoId, todo);
    }
    
    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);
    }
}
