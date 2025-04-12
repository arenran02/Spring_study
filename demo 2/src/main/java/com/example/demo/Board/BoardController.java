package com.example.demo.Board;

import com.example.demo.dto.board.BoardRequestDTO;
import com.example.demo.dto.board.BoardResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 1. POST /api/boards - 게시글 등록
    @PostMapping
    public ResponseEntity<BoardResponseDTO> createBoard(@RequestBody BoardRequestDTO dto) {
        return ResponseEntity.ok(boardService.createBoard(dto));
    }

    // 2. GET /api/boards - 전체 게시글 목록 조회
    @GetMapping
    public ResponseEntity<List<BoardResponseDTO>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }

    // 3. GET /api/boards/{boardId} - 게시글 단건 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDTO> getBoardById(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.getBoardById(boardId));
    }

    // 4. PUT /api/boards/{boardId} - 게시글 수정
    @PutMapping("/{boardId}")
    public ResponseEntity<BoardResponseDTO> updateBoard(@PathVariable Long boardId, @RequestBody BoardRequestDTO dto) {
        return ResponseEntity.ok(boardService.updateBoard(boardId, dto));
    }

    // 5. DELETE /api/boards/{boardId} - 게시글 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.noContent().build();
    }
}
