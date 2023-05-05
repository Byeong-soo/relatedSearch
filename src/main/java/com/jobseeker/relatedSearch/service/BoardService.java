package com.jobseeker.relatedSearch.service;

import com.jobseeker.relatedSearch.domain.Board;
import com.jobseeker.relatedSearch.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(Board board){
        boardRepository.save(board);
        return board.getId();
    }

    public Board findOne(Long id){
        return boardRepository.findOne(id);
    }

    public List<Board> findBoards(){
        return boardRepository.findAll();
    }

}
