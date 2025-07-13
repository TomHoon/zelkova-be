package com.my.zelkova_back.post.dto;

import com.my.zelkova_back.post.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostNavResponse {
    private Long id;
    private String title;
    private String createdAt;

    public static PostNavResponse from(Post post) {
        return PostNavResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .createdAt(post.getCreatedAt().toString())
                .build();
    }
}
