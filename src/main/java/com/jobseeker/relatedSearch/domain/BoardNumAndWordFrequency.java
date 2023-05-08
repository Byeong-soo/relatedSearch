package com.jobseeker.relatedSearch.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class BoardNumAndWordFrequency {

    @Id @GeneratedValue
    private Long Id;
    private Long boardNum;
    private int wordFrequency;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "inverted_index_id")
    private InvertedIndex invertedIndex;

    public BoardNumAndWordFrequency(Board board, int wordFrequency) {
        this.boardNum = board.getId();
        this.wordFrequency = wordFrequency;
    }

    public void setInvertedIndex(InvertedIndex invertedIndex){
        this.invertedIndex = invertedIndex;
    }
}
