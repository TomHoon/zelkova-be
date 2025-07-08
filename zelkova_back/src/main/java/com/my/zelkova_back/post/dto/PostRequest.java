package com.my.zelkova_back.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    private Long boardId;
    private Long memberId;
    private String title;
    private String content;
}
