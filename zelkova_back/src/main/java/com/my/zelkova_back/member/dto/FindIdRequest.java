package com.my.zelkova_back.member.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindIdRequest {

	private String name;
	private String phoneNumber;
	private LocalDate birthdate;
	
}
