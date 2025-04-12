package com.example.demo.Like;

import com.example.demo.Board.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.LikeRepository;
import com.example.demo.repository.UserRepository;

import com.example.demo.User.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.like.LikeResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public LikeService(LikeRepository likeRepository,
                       UserRepository userRepository,
                       BoardRepository boardRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    // 1. 좋아요 등록
    public LikeResponseDTO likeBoard(Long userId, Long boardId) {
        // 중복 방지
        if (likeRepository.findByUserIdAndBoardId(userId, boardId).isPresent()) {
            throw new IllegalStateException("이미 좋아요를 눌렀습니다.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found"));

        Like like = new Like();
        like.setUser(user);
        like.setBoard(board);
        Like saved = likeRepository.save(like);

        return toResponseDTO(saved);
    }

    // 2. 좋아요 취소
    public void unlikeBoard(Long userId, Long boardId) {
        Like like = likeRepository.findByUserIdAndBoardId(userId, boardId)
                .orElseThrow(() -> new EntityNotFoundException("좋아요를 누른 기록이 없습니다."));

        likeRepository.delete(like);
    }

    // 3. 게시글에 눌린 좋아요 목록 조회
    public List<LikeResponseDTO> getLikesByBoard(Long boardId) {
        return likeRepository.findByBoardId(boardId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 4. 유저가 좋아요한 게시글 목록 조회
    public List<LikeResponseDTO> getLikesByUser(Long userId) {
        return likeRepository.findByUserId(userId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 유틸
    private LikeResponseDTO toResponseDTO(Like like) {
        return new LikeResponseDTO(
                like.getUser().getId(),
                like.getUser().getName(),
                like.getBoard().getId(),
                like.getLikedAt()
        );
    }
}
