package com.jobseeker.relatedSearch.service;

import com.jobseeker.relatedSearch.domain.Board;
import com.jobseeker.relatedSearch.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    BoardService boardService;
    @Autowired WordAnalysisService wordAnalysisService;
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

    @Test
    void contentRepeatCount() throws Exception {
        //given
        String title = "테스트 분류";
        String content = "안녕하세요 저는 취업 준비생 입니다. 한국글로벌널리지네트웍 과제 진행 중입니다.취업 화이팅";
        //when
        Board board = new Board(title, content, LocalDateTime.now());
        Map<String, Integer> stringIntegerMap = wordAnalysisService.doWordAnalysis(content);

        //then
        assertEquals(stringIntegerMap.get("취업"),2);
        assertEquals(stringIntegerMap.get("진행"),1);
        assertEquals(stringIntegerMap.get("과제"),1);
    }
}