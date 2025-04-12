package com.example.demo.Like;

import com.example.demo.dto.like.LikeResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    // 1. POST /api/boards/{boardId}/likes - 게시글 좋아요 등록
    @PostMapping("/api/boards/{boardId}/likes")
    public ResponseEntity<LikeResponseDTO> likeBoard(
            @PathVariable Long boardId,
            @RequestParam Long userId // 쿼리 파라미터로 유저 ID 전달
    ) {
        return ResponseEntity.ok(likeService.likeBoard(userId, boardId));
    }

    // 2. DELETE /api/boards/{boardId}/likes - 좋아요 취소
    @DeleteMapping("/api/boards/{boardId}/likes")
    public ResponseEntity<Void> unlikeBoard(
            @PathVariable Long boardId,
            @RequestParam Long userId
    ) {
        likeService.unlikeBoard(userId, boardId);
        return ResponseEntity.noContent().build();
    }

    // 3. GET /api/boards/{boardId}/likes - 게시글 좋아요 목록 조회
    @GetMapping("/api/boards/{boardId}/likes")
    public ResponseEntity<List<LikeResponseDTO>> getLikesByBoard(
            @PathVariable Long boardId
    ) {
        return ResponseEntity.ok(likeService.getLikesByBoard(boardId));
    }

    // 4. GET /api/users/{userId}/likes - 유저가 누른 좋아요 목록 조회
    @GetMapping("/api/users/{userId}/likes")
    public ResponseEntity<List<LikeResponseDTO>> getLikesByUser(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(likeService.getLikesByUser(userId));
    }
}
