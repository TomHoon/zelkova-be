package com.my.zelkova_back.post.dto;

import com.my.zelkova_back.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDetailResponse {
    private Long postId;
    private String title;
    private String content;
    private String writerName;
    private LocalDateTime createdAt;
    private Integer viewCount;

    public static PostDetailResponse from(Post post) {
        PostDetailResponse response = new PostDetailResponse();
        response.postId = post.getId();
        response.title = post.getTitle();
        response.content = post.getContent();
        response.writerName = post.getUser().getNickname();
        response.createdAt = post.getCreatedAt();
        response.viewCount = post.getViewCount();
        return response;
    }
}
