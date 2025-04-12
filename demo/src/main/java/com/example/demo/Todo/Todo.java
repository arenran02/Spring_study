package com.example.demo.Todo;

import com.example.demo.User.User;
import jakarta.persistence.*;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String todo;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public String getTodo() {
        return todo;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
