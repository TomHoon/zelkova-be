package com.example.demo.board.model;

import lombok.Data;

@Data
public class Board {
    private Long id;
    private Long categoryId;
    private String boardName;
}
