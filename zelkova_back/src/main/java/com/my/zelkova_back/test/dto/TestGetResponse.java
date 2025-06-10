package com.my.zelkova_back.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TestGetResponse {

	private String username;
	private String email;
	
}
