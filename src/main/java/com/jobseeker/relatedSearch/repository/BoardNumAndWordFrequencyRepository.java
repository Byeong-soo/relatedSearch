package com.jobseeker.relatedSearch.repository;

import com.jobseeker.relatedSearch.domain.BoardNumAndWordFrequency;
import com.jobseeker.relatedSearch.domain.InvertedIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardNumAndWordFrequencyRepository extends JpaRepository<BoardNumAndWordFrequency, Long> {
    BoardNumAndWordFrequency findByBoardNumAndInvertedIndex(Long boardNum, InvertedIndex invertedIndex);
    Integer countByInvertedIndex(InvertedIndex invertedIndex);

    List<BoardNumAndWordFrequency> findByInvertedIndex(InvertedIndex invertedIndex);
}

