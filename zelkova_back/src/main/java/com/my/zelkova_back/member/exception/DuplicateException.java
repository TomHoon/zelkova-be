package com.my.zelkova_back.member.exception;

import org.springframework.http.HttpStatus;

import com.my.zelkova_back.common.exception.CustomException;
import com.my.zelkova_back.common.response.ResponseCode;

public class DuplicateException extends CustomException {
	public DuplicateException(String message) {
		super(ResponseCode.CONFLICT, message);
	}
}
