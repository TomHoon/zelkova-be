package com.my.zelkova_back.reply.dto;

import com.my.zelkova_back.reply.entity.Reply;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReplyResponse {
	private Long id;
	private Long commentId;
	private Long memberId;
	private String username;
	private String content;
	private String createdAt;

	public static ReplyResponse from(Reply reply) {
		return ReplyResponse.builder()
				.id(reply.getId())
				.commentId(reply.getComment().getId())
				.memberId(reply.getMember().getId())
				.username(reply.getMember().getUsername())
				.content(reply.getContent())
				.createdAt(reply.getCreatedAt().toString())
				.build();
	}
}
