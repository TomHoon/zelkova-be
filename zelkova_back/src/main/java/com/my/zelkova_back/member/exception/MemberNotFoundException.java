package com.my.zelkova_back.member.exception;

import org.springframework.http.HttpStatus;

import com.my.zelkova_back.common.exception.CustomException;

public class MemberNotFoundException extends CustomException {
	public MemberNotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND); 
	}
}
