package com.my.zelkova_back.post.dto;

import com.my.zelkova_back.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostEditResponse {
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime updatedAt;

    public static PostEditResponse from(Post post) {
        PostEditResponse response = new PostEditResponse();
        response.postId = post.getId();
        response.title = post.getTitle();
        response.content = post.getContent();
        response.updatedAt = post.getUpdatedAt();
        return response;
    }
}
