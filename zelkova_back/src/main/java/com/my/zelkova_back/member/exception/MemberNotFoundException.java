package com.my.zelkova_back.member.exception;

import org.springframework.http.HttpStatus;

import com.my.zelkova_back.common.exception.CustomException;
import com.my.zelkova_back.common.response.ResponseCode;

public class MemberNotFoundException extends CustomException {
	public MemberNotFoundException(String message) {
		super(ResponseCode.NOT_FOUND, message); 
	}
}
