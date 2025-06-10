package com.my.zelkova_back.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestJoinRequest {
	private String username;
	private String password;
	private String email;
}
