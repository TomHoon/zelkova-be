package com.my.zelkova_back.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateRequest {
    private Long postId;
    private String title;
    private String content;
}
