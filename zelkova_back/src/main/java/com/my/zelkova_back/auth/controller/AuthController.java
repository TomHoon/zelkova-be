package com.my.zelkova_back.auth.controller;



import com.my.zelkova_back.auth.dto.LoginRequest;
import com.my.zelkova_back.auth.dto.LoginResponse;
import com.my.zelkova_back.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}
}
