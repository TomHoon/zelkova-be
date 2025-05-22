package com.example.demo.board.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Like {
    private Long id;
    private Long userId;
    private Long postId;
    private LocalDateTime likedAt;
}

