package com.my.zelkova_back.comment.dto;

import java.time.LocalDateTime;

import com.my.zelkova_back.comment.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {
	private Long id;
	private Long postId;
	private Long userId;
	private String username;
	private String content;
	private String createdAt;
	
	public static CommentResponse from(Comment comment) {
		return CommentResponse.builder()
				.id(comment.getId())
				.postId(comment.getPost().getId())
				.userId(comment.getMember().getId())
				.username(comment.getMember().getUsername())
				.content(comment.getContent())
				.createdAt(comment.getCreatedAt().toString())
				.build();
	}
}
