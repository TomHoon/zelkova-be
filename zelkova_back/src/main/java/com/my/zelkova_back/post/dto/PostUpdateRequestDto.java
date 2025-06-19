package com.my.zelkova_back.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateRequestDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
}