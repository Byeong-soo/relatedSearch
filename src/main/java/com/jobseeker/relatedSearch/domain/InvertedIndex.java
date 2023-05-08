package com.jobseeker.relatedSearch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InvertedIndex {

    @Id @GeneratedValue
    @Column(name = "inverted_index_id")
    private Long id;

    private String word;

    @OneToMany(mappedBy = "invertedIndex",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<BoardNumAndWordFrequency> boardAndCount = new ArrayList<>();

    @OneToMany(mappedBy = "invertedIndex",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<BoardInvertedIndex> boardInvertedIndex = new ArrayList<>();


    public void addBoardInvertedIndex(BoardInvertedIndex... boardInvertedIndex) {
        Collections.addAll(this.boardInvertedIndex, boardInvertedIndex);
    }

    public void addBoardAndCount(BoardNumAndWordFrequency boardNumAndWordFrequency) {
        this.boardAndCount.add(boardNumAndWordFrequency);
        boardNumAndWordFrequency.setInvertedIndex(this);
    }

    public InvertedIndex(String word) {
        this.word = word;
    }

    public static InvertedIndex createInvertedIndex(String word, BoardNumAndWordFrequency... boardNumANdWordFrequencies) {
        InvertedIndex invertedIndex = new InvertedIndex(word);
        for (BoardNumAndWordFrequency boardNumANdWordFrequency : boardNumANdWordFrequencies) {
            invertedIndex.addBoardAndCount(boardNumANdWordFrequency);
        }
        return invertedIndex;
    }

}
