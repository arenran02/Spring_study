package com.example.demo.Like;

import com.example.demo.Board.Board;
import com.example.demo.User.User;
import jakarta.persistence.*;

        import java.time.LocalDateTime;

@Entity
@Table(name = "likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "board_id"})
})
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    private LocalDateTime likedAt;

    @PrePersist
    public void onLike() {
        this.likedAt = LocalDateTime.now();
    }

    // Getter / Setter
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Board getBoard() {
        return board;
    }

    public LocalDateTime getLikedAt() {
        return likedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
