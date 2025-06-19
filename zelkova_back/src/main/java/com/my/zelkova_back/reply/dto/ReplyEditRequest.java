package com.my.zelkova_back.reply.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyEditRequest {

	private Long id;
	private String content;
}
