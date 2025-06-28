package com.my.zelkova_back.auth.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class JwtUtilTest {

	private final JwtUtil jwtUtil = new JwtUtil("my-super-secure-test-key-123456789012", 1000L * 60 * 30, 1000L * 60 * 60 * 24 * 7);

	@Test
	void accessToken_생성_정상() {
		String token = jwtUtil.createAccessToken("testuser");
		assertNotNull(token);
	}

	@Test
	void refreshToken_생성_정상() {
		String token = jwtUtil.createRefreshToken("testuser");
		assertNotNull(token);
	}

	@Test
	void 토큰에서_유저이름_추출() {
		String token = jwtUtil.createAccessToken("testuser");
		String username = jwtUtil.getUsername(token);
		assertEquals("testuser", username);
	}

	@Test
	void 유효한_토큰_검증() {
		String token = jwtUtil.createAccessToken("testuser");
		assertTrue(jwtUtil.isValid(token));
	}

	@Test
	void 만료된_토큰_검증() throws InterruptedException {
		JwtUtil shortJwtUtil = new JwtUtil("short", 100, 1000); // 100ms
		String token = shortJwtUtil.createAccessToken("expireduser");
		Thread.sleep(300);
		assertFalse(shortJwtUtil.isValid(token));
	}
}
