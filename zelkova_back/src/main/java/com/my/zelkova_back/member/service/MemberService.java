package com.my.zelkova_back.member.service;

import com.my.zelkova_back.member.dto.*;

public interface MemberService {
	String join(JoinRequest request);

	Object login(LoginRequest request);

	String findId(FindIdRequest request);

	void sendResetPasswordMail(FindPwRequest request);

	ProfileResponse getProfileByNickname(String nickname);

	void updateProfile(UpdateProfileRequest request);

	Object kakaoLogin(KakaoLoginRequest request);
}