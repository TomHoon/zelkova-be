package com.my.zelkova_back.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// 커스텀 예외 처리
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<String> handleCustomException(CustomException ex) {
		return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
	}

	// 알 수 없는 예외 처리
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleUnknownException(Exception ex) {
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("서버 오류가 발생했습니다.");
	}
}
