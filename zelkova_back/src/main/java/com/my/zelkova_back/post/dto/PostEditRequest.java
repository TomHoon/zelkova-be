package com.my.zelkova_back.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostEditRequest {
    private Long id;
    private String title;
    private String content;
}