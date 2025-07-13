package com.my.zelkova_back.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter, setter, toString, equals, hashCode 자동 생성
@NoArgsConstructor // 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드를 받는 생성자 자동 생성
public class KakaoLoginRequest {
    private String accessToken;
    private String email;
    private String nickname;
    private String kakaoId;
}