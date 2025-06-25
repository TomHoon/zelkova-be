package com.my.zelkova_back.common.exception;

import com.my.zelkova_back.common.response.ApiResponse;
import com.my.zelkova_back.common.response.ResponseCode;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ApiResponse<?>> handleCustomException(CustomException ex) {
		String message = (ex.getCustomMessage() != null)
			? ex.getCustomMessage()
			: ex.getCode().getMessage();

		return ResponseEntity
			.status(ex.getStatus())
			.body(new ApiResponse<>(ex.getCode().getCode(), message, null));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> handleUnknownException(Exception ex) {
		ex.printStackTrace();
		ResponseCode code = ResponseCode.INTERNAL_SERVER_ERROR;
		return ResponseEntity
			.status(code.getStatus())
			.body(ApiResponse.error(code));
	}
}
