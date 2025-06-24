package com.my.zelkova_back.common.exception;

import com.my.zelkova_back.common.response.ResponseCode;

public class InvalidInputException extends CustomException {
	public InvalidInputException(String message) {
		super(ResponseCode.BAD_REQUEST, message);
	}
}
