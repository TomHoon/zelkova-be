package com.my.zelkova_back.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {

	// 일반 성공
	SUCCESS("SUCCESS", "요청에 성공했습니다.", HttpStatus.OK),

	// 요청 오류
	BAD_REQUEST("ERROR400", "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
	UNAUTHORIZED("ERROR401", "인증이 필요합니다.", HttpStatus.UNAUTHORIZED),
	FORBIDDEN("ERROR403", "접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
	NOT_FOUND("ERROR404", "요청한 리소스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
	CONFLICT("ERROR409", "중복된 데이터가 있습니다.", HttpStatus.CONFLICT),
	
	// 유저 관련
	USER_NOT_FOUND("ERROR1001", "해당 사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
	DUPLICATE_EMAIL("ERROR1002", "이미 사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST),
	INVALID_PASSWORD("ERROR1003", "비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
	INVALID_EMAIL_FORMAT("ERROR1004", "이메일 형식이 올바르지 않습니다.", HttpStatus.BAD_REQUEST),
	INVALID_USERNAME("ERROR1005", "아이디는 8자 이상이어야 합니다.", HttpStatus.BAD_REQUEST),
	DUPLICATE_USERNAME("ERROR1006", "이미 존재하는 아이디입니다.", HttpStatus.CONFLICT),
	DUPLICATE_NICKNAME("ERROR1007", "이미 존재하는 닉네임입니다.", HttpStatus.CONFLICT),
	DUPLICATE_PHONE_NUMBER("ERROR1008", "이미 존재하는 전화번호입니다.", HttpStatus.CONFLICT),
	INVALID_INPUT("ERROR1009", "입력값이 올바르지 않습니다.", HttpStatus.BAD_REQUEST),

	// 인증/토큰 관련
	INVALID_TOKEN("ERROR2001", "유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED),
	EXPIRED_TOKEN("ERROR2002", "토큰이 만료되었습니다.", HttpStatus.UNAUTHORIZED),
	ACCESS_DENIED("ERROR2003", "권한이 없습니다.", HttpStatus.FORBIDDEN),

	// 서버 오류
	INTERNAL_SERVER_ERROR("ERROR500", "서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

	private final String code;
	private final String message;
	private final HttpStatus status;

	ResponseCode(String code, String message, HttpStatus status) {
		this.code = code;
		this.message = message;
		this.status = status;
	}
}
