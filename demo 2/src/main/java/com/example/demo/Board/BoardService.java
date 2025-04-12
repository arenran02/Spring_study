package com.example.demo.Board;

import com.example.demo.User.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.BoardRepository;
import com.example.demo.dto.board.BoardRequestDTO;
import com.example.demo.dto.board.BoardResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    // 1. 게시글 등록
    public BoardResponseDTO createBoard(BoardRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("작성자(User)를 찾을 수 없습니다."));

        Board board = new Board();
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setUser(user);

        Board saved = boardRepository.save(board);
        return toResponseDTO(saved);
    }

    // 2. 전체 게시글 조회
    public List<BoardResponseDTO> getAllBoards() {
        return boardRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 3. 게시글 단건 조회
    public BoardResponseDTO getBoardById(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));
        return toResponseDTO(board);
    }

    // 4. 게시글 수정
    public BoardResponseDTO updateBoard(Long boardId, BoardRequestDTO dto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());

        return toResponseDTO(boardRepository.save(board));
    }

    // 5. 게시글 삭제
    public void deleteBoard(Long boardId) {
        if (!boardRepository.existsById(boardId)) {
            throw new EntityNotFoundException("게시글을 찾을 수 없습니다.");
        }
        boardRepository.deleteById(boardId);
    }

    // 유틸: Entity → DTO 변환
    private BoardResponseDTO toResponseDTO(Board board) {
        return new BoardResponseDTO(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getUser().getName(),
                board.getCreatedAt()
        );
    }
}
