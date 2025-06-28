package com.my.zelkova_back.common.exception;

import org.springframework.http.HttpStatus;

import com.my.zelkova_back.common.response.ResponseCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

	private final ResponseCode code;
	private final String customMessage;

	public CustomException(ResponseCode code) {
		super(code.getMessage());
		this.code = code;
		this.customMessage = null;
	}

	public CustomException(ResponseCode code, String customMessage) {
		super(customMessage);
		this.code = code;
		this.customMessage = customMessage;
	}

	public HttpStatus getStatus() {
		return code.getStatus();
	}
}
