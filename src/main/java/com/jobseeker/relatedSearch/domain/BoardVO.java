package com.jobseeker.relatedSearch.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class BoardVO {

    private Board board;
    private List<Board> relatedBoards;

    public BoardVO(Board board, List<Board> relatedBoards) {
        this.board = board;
        this.relatedBoards = relatedBoards;
    }
}
