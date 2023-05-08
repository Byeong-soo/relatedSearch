package com.jobseeker.relatedSearch.service;

import com.jobseeker.relatedSearch.domain.BoardInvertedIndex;
import com.jobseeker.relatedSearch.repository.BoardInvertedIndexRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardInvertedIndexService {

    private final BoardInvertedIndexRepository boardInvertedIndexRepository;

    public List<BoardInvertedIndex> findByBoardId(Long boardId){
        return boardInvertedIndexRepository.findByBoardId(boardId);
    }


}
