package com.my.zelkova_back.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

	private String code;
	private String message;
	private T data;

	public static <T> ApiResponse<T> success(ResponseCode code, T data) {
		return new ApiResponse<>(code.getCode(), code.getMessage(), data);
	}

	public static <T> ApiResponse<T> success(ResponseCode code) {
		return new ApiResponse<>(code.getCode(), code.getMessage(), null);
	}

	public static <T> ApiResponse<T> error(ResponseCode code) {
		return new ApiResponse<>(code.getCode(), code.getMessage(), null);
	}

	public static <T> ApiResponse<T> error(ResponseCode code, T data) {
		return new ApiResponse<>(code.getCode(), code.getMessage(), data);
	}
}
