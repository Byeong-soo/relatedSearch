package com.jobseeker.relatedSearch.repository;

import com.jobseeker.relatedSearch.domain.BoardInvertedIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardInvertedIndexRepository extends JpaRepository<BoardInvertedIndex, Long> {
    List<BoardInvertedIndex> findByBoardId(Long boardId);
}
