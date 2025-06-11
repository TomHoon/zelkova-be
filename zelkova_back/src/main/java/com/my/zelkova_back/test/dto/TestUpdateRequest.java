package com.my.zelkova_back.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestUpdateRequest {
	
	private Long id;
	private String password;

}