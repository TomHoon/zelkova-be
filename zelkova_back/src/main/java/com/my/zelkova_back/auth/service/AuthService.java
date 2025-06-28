package com.my.zelkova_back.auth.service;

import org.springframework.stereotype.Service;

import com.my.zelkova_back.auth.dto.LoginRequest;
import com.my.zelkova_back.auth.dto.LoginResponse;
import com.my.zelkova_back.auth.repository.RefreshTokenRepository;
import com.my.zelkova_back.auth.token.JwtUtil;
import com.my.zelkova_back.auth.token.RefreshToken;
import com.my.zelkova_back.user.entity.User;
import com.my.zelkova_back.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtUtil jwtutil;
	private final RefreshTokenRepository refreshTokenRepository;
	private final UserRepository userRepository;
	//private final PasswordEncoder passwordEncoder;
	
	public LoginResponse login(LoginRequest request) {
		String username = request.getUsername();

		//사용자 조회
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

		//비밀번호 확인 -> encoder 작업 끝난 후 통합시켜야함
//		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
//		}

		// 토큰 생성
		String accessToken = jwtutil.createAccessToken(username);
		String refreshToken = jwtutil.createRefreshToken(username);

		// RefreshToken 저장
		refreshTokenRepository.save(new RefreshToken(username, refreshToken));

		// 응답 반환
		return new LoginResponse(accessToken, refreshToken);
	}
}
