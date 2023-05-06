package com.jobseeker.relatedSearch.controller;

import com.jobseeker.relatedSearch.domain.Board;
import com.jobseeker.relatedSearch.domain.BoardDTO;
import com.jobseeker.relatedSearch.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public List<Board> boardMain() {
        return boardService.findBoards();
    }

    @PostMapping("board/new")
    public ResponseEntity<Board> create(BoardDTO boardDTO){

            Board newBoard = new Board(boardDTO.getTitle(),boardDTO.getContent(), LocalDateTime.now());
            return ResponseEntity.ok(newBoard);
    }
}
