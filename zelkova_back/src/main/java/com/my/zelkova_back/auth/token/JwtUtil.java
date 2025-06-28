package com.my.zelkova_back.auth.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
	private final String secretKey;
	private final long accessTokenExp;
	private final long refreshTokenExp;

	public JwtUtil() {
		this.secretKey = Base64.getEncoder().encodeToString("my-secret-key".getBytes());
		this.accessTokenExp = 1000L * 60 * 30; // 30분
		this.refreshTokenExp = 1000L * 60 * 60 * 24 * 7; // 7일
	}

	// ✅ 2. 테스트에서 사용할 수 있는 커스텀 생성자
	public JwtUtil(String secretKey, long accessTokenExp, long refreshTokenExp) {
		this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		this.accessTokenExp = accessTokenExp;
		this.refreshTokenExp = refreshTokenExp;
	}
	
	public String createAccessToken(String username) {
		return createToken(username, accessTokenExp);
	}

	public String createRefreshToken(String username) {
		return createToken(username, refreshTokenExp);
	}

	private String createToken(String subject, long expTime) {
		Date now = new Date();
		return Jwts.builder()
			.setSubject(subject)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + expTime))
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}
	public String getUsername(String token) {
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

	// ✅ 토큰 유효성 검사
	public boolean isValid(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
