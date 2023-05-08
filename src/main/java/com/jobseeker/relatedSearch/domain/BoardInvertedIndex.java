package com.jobseeker.relatedSearch.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class BoardInvertedIndex {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id")
    @JsonIgnore
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "inverted_index_id")
    private InvertedIndex invertedIndex;

    public BoardInvertedIndex(Board board, InvertedIndex invertedIndex) {
        this.board = board;
        this.invertedIndex = invertedIndex;
    }
}
