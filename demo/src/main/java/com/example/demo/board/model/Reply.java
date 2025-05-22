package com.example.demo.board.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Reply {
    private Long id;
    private Long commentId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Boolean isDeleted;
}

