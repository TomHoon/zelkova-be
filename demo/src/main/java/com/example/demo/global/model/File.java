package com.example.demo.global.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class File {
    private Long id;
    private String fileName;
    private String filePath;
    private Long postId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private Boolean isDeleted;
}

