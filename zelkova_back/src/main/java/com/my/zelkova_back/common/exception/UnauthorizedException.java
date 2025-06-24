package com.my.zelkova_back.common.exception;

import com.my.zelkova_back.common.response.ResponseCode;

public class UnauthorizedException extends CustomException {
	public UnauthorizedException() {
		super(ResponseCode.UNAUTHORIZED, "권한이 없습니다");
	}
}
