package com.example.demo.dto.board;

import java.time.LocalDateTime;

public class BoardResponseDTO {

    private Long id;
    private String title;
    private String content;
    private String userName;
    private LocalDateTime createdAt;

    public BoardResponseDTO(Long id, String title, String content, String userName, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
