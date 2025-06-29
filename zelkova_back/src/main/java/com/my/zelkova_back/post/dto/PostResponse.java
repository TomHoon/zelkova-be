package com.my.zelkova_back.post.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class PostResponse {
    private Long postId;
    private String title;
    private String writerName;
    private LocalDateTime createdAt;
    private Integer viewCount;
}
