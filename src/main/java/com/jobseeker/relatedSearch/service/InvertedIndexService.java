package com.jobseeker.relatedSearch.service;

import com.jobseeker.relatedSearch.domain.Board;
import com.jobseeker.relatedSearch.domain.BoardInvertedIndex;
import com.jobseeker.relatedSearch.domain.BoardNumAndWordFrequency;
import com.jobseeker.relatedSearch.domain.InvertedIndex;
import com.jobseeker.relatedSearch.repository.BoardInvertedIndexRepository;
import com.jobseeker.relatedSearch.repository.InvertedIndexRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InvertedIndexService {

    private final InvertedIndexRepository invertedIndexRepository;
    private final BoardInvertedIndexRepository boardInvertedIndexRepository;
    @Transactional
    public Long save(InvertedIndex invertedIndex){
        invertedIndexRepository.save(invertedIndex);
        return invertedIndex.getId();
    }

    public Optional<InvertedIndex> findOne(String word){
        return invertedIndexRepository.findWord(word);
    }



    @Transactional
    public void saveAll(Board newBoard, Map<String, Integer> stringIntegerMap){
        for (String word : stringIntegerMap.keySet()) {
            BoardNumAndWordFrequency boardNumAndWordFrequency = new BoardNumAndWordFrequency(newBoard, stringIntegerMap.get(word));

            Optional<InvertedIndex> optional = findOne(word);
            InvertedIndex invertedIndex = optional.orElse(null);
            if(invertedIndex == null){
                invertedIndex = InvertedIndex.createInvertedIndex(word, boardNumAndWordFrequency);
            }else{
                invertedIndex.addBoardAndCount(boardNumAndWordFrequency);
            }
            BoardInvertedIndex boardInvertedIndex = new BoardInvertedIndex(newBoard, invertedIndex);
            boardInvertedIndexRepository.save(boardInvertedIndex);

            newBoard.addBoardInvertedIndex(boardInvertedIndex);
            invertedIndex.addBoardInvertedIndex(boardInvertedIndex);

            save(invertedIndex);
        }
    }
}
