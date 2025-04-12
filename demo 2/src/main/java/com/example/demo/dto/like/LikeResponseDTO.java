package com.example.demo.dto.like;

import java.time.LocalDateTime;

public class LikeResponseDTO {

    private Long userId;
    private String userName;
    private Long boardId;
    private LocalDateTime likedAt;

    public LikeResponseDTO(Long userId, String userName, Long boardId, LocalDateTime likedAt) {
        this.userId = userId;
        this.userName = userName;
        this.boardId = boardId;
        this.likedAt = likedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Long getBoardId() {
        return boardId;
    }

    public LocalDateTime getLikedAt() {
        return likedAt;
    }
}
