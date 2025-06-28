package com.my.zelkova_back.auth.token;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import com.my.zelkova_back.common.exception.CustomException;
import com.my.zelkova_back.common.response.ResponseCode;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

	private final SecretKey secretKey;
	private final long accessTokenExp;
	private final long refreshTokenExp;

	public JwtUtil() {
		this.secretKey = Keys.hmacShaKeyFor("my-very-secret-key-which-is-over-32-bytes!".getBytes());
		this.accessTokenExp = 1000L * 60 * 30; // 30분
		this.refreshTokenExp = 1000L * 60 * 60 * 24 * 7; // 7일
	}

	// 커스텀 생성자 (테스트용)
	public JwtUtil(String secret, long accessTokenExp, long refreshTokenExp) {
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
		this.accessTokenExp = accessTokenExp;
		this.refreshTokenExp = refreshTokenExp;
	}

	// 토큰 생성
	private String createToken(String subject, long expTime) {
		Date now = new Date();
		return Jwts.builder()
			.setSubject(subject)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + expTime))
			.signWith(secretKey, SignatureAlgorithm.HS256)
			.compact();
	}

	public String createAccessToken(String username) {
		return createToken(username, accessTokenExp);
	}

	public String createRefreshToken(String username) {
		return createToken(username, refreshTokenExp);
	}

	// 토큰에서 username(subject) 추출
	public String getUsername(String token) {
		return getClaimsFromToken(token).getSubject();
	}

	// Claims 추출
	public Claims getClaimsFromToken(String token) {
		try {
			return Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody();
		} catch (JwtException e) {
			throw new CustomException(ResponseCode.INVALID_TOKEN);
		}
	}

	// 유효성 검사
	public boolean isValid(String token) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
}
