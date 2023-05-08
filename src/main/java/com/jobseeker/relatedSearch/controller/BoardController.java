package com.jobseeker.relatedSearch.controller;

import com.jobseeker.relatedSearch.domain.*;
import com.jobseeker.relatedSearch.service.BoardService;
import com.jobseeker.relatedSearch.service.InvertedIndexService;
import com.jobseeker.relatedSearch.service.WordAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;
    private final InvertedIndexService invertedIndexService;
    private final WordAnalysisService wordAnalysisService;

    @GetMapping("/board/list")
    public List<Board> boardMain() {
        return boardService.findBoards();
    }

    @GetMapping("board/{boardId}")
    public BoardVO boardDetail(@PathVariable Long boardId) {
        Board board = boardService.findOne(boardId);
        List<Board> relatedBoard = boardService.checkFindRelatedBoard(boardId);
        return new BoardVO(board, relatedBoard);
    }

    @PostMapping("board/new")

    public ResponseEntity<Board> create(@RequestBody BoardDTO boardDTO) throws Exception {

        Board newBoard = new Board(boardDTO.getTitle(), boardDTO.getContent(), LocalDateTime.now());
        Map<String, Integer> stringIntegerMap = wordAnalysisService.doWordAnalysis(newBoard.getContent());

        Long save = boardService.save(newBoard);
        invertedIndexService.saveAll(newBoard, stringIntegerMap);

        return ResponseEntity.ok(newBoard);
    }
}
