package com.my.zelkova_back.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JoinRequest {

	private String username;
	private String password;
	private String phoneNumber;
	private String nickname;
	private String email;
	private String birthdate;
	private String name;
	private String mobileCarrier;
	private String socialType;
	private String socialId;
	
}
