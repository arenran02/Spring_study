package com.example.demo.repository;

import com.example.demo.Board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByUserId(Long userId); // 특정 유저가 작성한 게시글 조회용
}
