package com.my.zelkova_back.member.exception;

import org.springframework.http.HttpStatus;

import com.my.zelkova_back.common.exception.CustomException;

public class DuplicateUsernameException extends CustomException {
	public DuplicateUsernameException(String message) {
		super(message, HttpStatus.CONFLICT);
	}
}
