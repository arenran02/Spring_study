package com.example.demo.repository;

import com.example.demo.Board.Board;
import com.example.demo.User.User;
import com.example.demo.Like.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    // 특정 게시글에 대한 좋아요 목록
    List<Like> findByBoardId(Long boardId);

    // 특정 유저가 좋아요한 목록
    List<Like> findByUserId(Long userId);

    // 특정 유저가 특정 게시글을 좋아요 했는지 여부 확인
    Optional<Like> findByUserIdAndBoardId(Long userId, Long boardId);

    // 삭제 시에 findByUserIdAndBoardId 로 가져와서 delete
}
