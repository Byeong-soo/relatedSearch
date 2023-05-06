package com.jobseeker.relatedSearch.service;

import com.jobseeker.relatedSearch.domain.Board;
import com.jobseeker.relatedSearch.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired BoardService boardService;
    @Autowired BoardRepository boardRepository;
    @Autowired EntityManager em;

    @Test
    @DisplayName("글 작성 테스트")
    void boardSaveTest(){
        //given
        String title = "테스트 제목 1번";
        String content = "테스트 내용 1번";
        Board board = new Board(title, content, LocalDateTime.now());

        //when
        Long savedId = boardService.save(board);
        //then
        Board findBoard = boardService.findOne(savedId);
        assertEquals(board, findBoard,"같은 객체가 반환되어야 한다.");
        assertEquals(board.getTitle(), findBoard.getTitle(),"제목이 정상적으로 들어가야한다.");
        assertEquals(board.getContent(), findBoard.getContent(),"글 내용이 정상적으로 들어가야한다.");
    }

    @Test
    @DisplayName("글 전체 조회 테스트")
    void boardFindAllTest(){

        for (int i = 0; i < 100; i++) {
            String title = "테스트 제목 " + i + "번";
            String content = "테스트 내용 " + i + "번";
            Board board = new Board(title, content, LocalDateTime.now());
            boardService.save(board);
        }

        assertEquals(100, boardService.findBoards().size(),"100개의 글이 조회되어야 한다.");
    }
}