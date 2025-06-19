package com.my.zelkova_back.common.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CustomException {
	public UnauthorizedException() {
		super("권한이 없습니다", HttpStatus.UNAUTHORIZED);
	}
}
