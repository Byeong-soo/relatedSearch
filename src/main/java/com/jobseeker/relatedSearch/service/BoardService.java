package com.jobseeker.relatedSearch.service;

import com.jobseeker.relatedSearch.domain.*;
import com.jobseeker.relatedSearch.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardInvertedIndexService boardInvertedIndexService;
    private final BoardNumAndWordFrequencyService boardNumAndWordFrequencyService;

    @Transactional
    public Long save(Board board) {
        boardRepository.save(board);
        return board.getId();
    }

    public Board findOne(Long id) {
        return boardRepository.findOne(id);
    }

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    public List<Board> checkFindRelatedBoard(Long boardId) {
        Long total = boardRepository.count();

        if (total <= 0) return new ArrayList<>();

        List<BoardInvertedIndex> byBoardId = boardInvertedIndexService.findByBoardId(boardId);
        List<List<Object>> related = new ArrayList<>();

        for (BoardInvertedIndex boardInvertedIndex : byBoardId) {
            Integer count = boardNumAndWordFrequencyService.countByInvertedIndex(boardInvertedIndex.getInvertedIndex());
            List<BoardNumAndWordFrequency> boardList;
            if(count >0){
                boardList = boardNumAndWordFrequencyService.findByInvertedIndex(boardInvertedIndex.getInvertedIndex());
            }else{
                boardList = new ArrayList<>();
            }
            Double ratio = (double) count / total;
            System.out.println(" = " + boardInvertedIndex.getInvertedIndex().getWord());
            System.out.println("total = " + total);
            System.out.println("count = " + count);
            System.out.println("ratio = " + ratio);
            // 전체 게시글중 60% 이상 제외? 아니면 젠체게시글이 하나의 게시글 내에서라는 말인지?
            if (ratio <= 0.4 && !(ratio >= 0.6)) {

                for (BoardNumAndWordFrequency numAndCount : boardList) {
                    if (numAndCount.getBoardNum().equals(boardId)) continue;
                    List<Object> boardAndCount = new ArrayList<>();
                    boardAndCount.add(boardRepository.findOne(numAndCount.getBoardNum()));
                    boardAndCount.add(count);
                    related.add(boardAndCount);

                    System.out.println("연관 단어 = " + boardInvertedIndex.getInvertedIndex().getWord());
                }

            }
        }

        Map<Board, Integer> boardList = new HashMap<>();
        Map<Board, Integer> temp = new HashMap<>();
        if (!related.isEmpty()) {
            for (List<Object> boardAndCount : related) {
                Board board = (Board) boardAndCount.get(0);
                Integer count = (Integer) boardAndCount.get(1);

                if (temp.containsKey(board)) {
                    temp.put(board, Math.max(temp.get(board),count));
                    boardList.put(board, temp.get(board));
                } else {
                    temp.put(board, count);
                }
            }
            return boardList.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue)).map(Map.Entry::getKey).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }


}
