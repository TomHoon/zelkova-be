package com.example.demo.board.model;

import java.time.LocalDateTime;

public class Post {
    private Long id;
    private Long boardId;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Boolean isDeleted;
    private Integer viewCount;
}
