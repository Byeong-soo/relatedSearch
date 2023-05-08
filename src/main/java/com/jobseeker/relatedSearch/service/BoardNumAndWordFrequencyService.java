package com.jobseeker.relatedSearch.service;


import com.jobseeker.relatedSearch.domain.BoardNumAndWordFrequency;
import com.jobseeker.relatedSearch.domain.InvertedIndex;
import com.jobseeker.relatedSearch.repository.BoardNumAndWordFrequencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardNumAndWordFrequencyService {

    private final BoardNumAndWordFrequencyRepository boardNumAndWordFrequencyRepository;

    public BoardNumAndWordFrequency findByBoardNumAndInvertedIndex(Long boardNum, InvertedIndex invertedIndex){
        return boardNumAndWordFrequencyRepository.findByBoardNumAndInvertedIndex(boardNum, invertedIndex);
    }

    public Integer countByInvertedIndex(InvertedIndex invertedIndex){
        return boardNumAndWordFrequencyRepository.countByInvertedIndex(invertedIndex);
    }

    public List<BoardNumAndWordFrequency> findByInvertedIndex(InvertedIndex invertedIndex){
        return boardNumAndWordFrequencyRepository.findByInvertedIndex(invertedIndex);
    }



}
