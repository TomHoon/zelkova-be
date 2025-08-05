package com.my.zelkova_back.member.service;

import com.my.zelkova_back.auth.dto.LoginRequest; // 로그인 요청 DTO
import com.my.zelkova_back.auth.dto.LoginResponse; // 로그인 응답 DTO
import com.my.zelkova_back.member.dto.FindIdRequest;
import com.my.zelkova_back.member.dto.FindPwRequest;
import com.my.zelkova_back.member.dto.JoinRequest;
import com.my.zelkova_back.member.dto.KakaoLoginRequest; // 카카오 로그인 요청 DTO
import com.my.zelkova_back.member.dto.ProfileResponse;
import com.my.zelkova_back.member.dto.UpdateProfileRequest;
import com.my.zelkova_back.member.entity.Member; // Member 엔티티
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberService {

    String join(JoinRequest request); // 회원가입

    LoginResponse login(LoginRequest request); // 일반 로그인

    String findId(FindIdRequest request); // 아이디 찾기

    void sendResetPasswordMail(FindPwRequest request); // 비밀번호 초기화 메일

    ProfileResponse getProfileByNickname(String nickname); // 닉네임으로 프로필 조회

    void updateProfile(UpdateProfileRequest request); // 프로필 수정
  
    Object kakaoLogin(KakaoLoginRequest request); // 카카오 로그인

    String withdrawMember(UserDetails userDetails); // 회원 탈퇴

    Member findByUsername(String username); // 유저 이름으로 Member 조회
}
