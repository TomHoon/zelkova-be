package com.my.zelkova_back.member.service;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.my.zelkova_back.common.exception.CustomException;
import com.my.zelkova_back.common.response.ResponseCode;
import com.my.zelkova_back.member.dto.FindIdRequest;
import com.my.zelkova_back.member.dto.FindPwRequest;
import com.my.zelkova_back.member.dto.JoinRequest;
import com.my.zelkova_back.member.dto.LoginRequest;
import com.my.zelkova_back.member.dto.ProfileResponse;
import com.my.zelkova_back.member.dto.UpdateProfileRequest;
import com.my.zelkova_back.member.entity.Member;
import com.my.zelkova_back.member.entity.Role;
import com.my.zelkova_back.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	// 시큐리티 비밀번호 암호화 추가
	private final PasswordEncoder passwordEncoder;

	public String join(JoinRequest request) {

		// 아이디 자릿수 검사
		if (request.getUsername().length() < 8) {
			throw new CustomException(ResponseCode.INVALID_USERNAME);
		}

		// 이메일 유효성 검사
		if (!request.getEmail().matches("^[a-zA-Z0-9]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$")) {
			throw new CustomException(ResponseCode.INVALID_EMAIL_FORMAT);
		}

		if (!request.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,72}$")) {
			throw new CustomException(ResponseCode.INVALID_PASSWORD);
		}

		// 중복 username 검사
		if (memberRepository.existsByUsername(request.getUsername())) {
			throw new CustomException(ResponseCode.DUPLICATE_USERNAME);
		}

		// 중복 닉네임 검사
		if (memberRepository.existsByNickname(request.getNickname())) {
			throw new CustomException(ResponseCode.DUPLICATE_NICKNAME);
		}

		// 중복 전화번호 검사
		if (memberRepository.existsByPhoneNumber(request.getPhoneNumber())) {
			throw new CustomException(ResponseCode.DUPLICATE_PHONE_NUMBER);
		}

		// 비밀번호 암호화
		String encodedPassword = passwordEncoder.encode(request.getPassword());

		// User 객체 생성
		Member member = Member.builder()
				.username(request.getUsername())
				.password(encodedPassword) // 암호화된 비밀번호만 저장하기
				.phoneNumber(request.getPhoneNumber())
				.nickname(request.getNickname())
				.email(request.getEmail())
				.birthdate(LocalDate.parse(request.getBirthdate()))
				.name(request.getName())
				.mobileCarrier(request.getMobileCarrier())
				.socialType(request.getSocialType())
				.socialId(request.getSocialId())
				.role(Role.ROLE_USER) // 기본 권한 추가, enum 객체 넘기기
				.build();

		memberRepository.save(member);

		return "회원가입에 성공하였습니다!";
	}

	public Object login(LoginRequest request) {
		return null;
	}

	public String findId(FindIdRequest request) {
		// 유효성 체크
		if (request.getName() == null || request.getPhoneNumber() == null || request.getBirthdate() == null) {
			throw new CustomException(ResponseCode.INVALID_INPUT);
		}

		// 회원 조회
		Member member = memberRepository.findByNameAndPhoneNumberAndBirthdate(
				request.getName(),
				request.getPhoneNumber(),
				request.getBirthdate()).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));

		return member.getUsername();
	}

	public void sendResetPasswordMail(FindPwRequest request) {
	}

	public ProfileResponse getProfileByNickname(String nickname) {

		// 닉네임 유효성 체크
		if (nickname == null || nickname.isBlank()) {
			throw new CustomException(ResponseCode.INVALID_INPUT);
		}

		// 회원 조회
		Member member = memberRepository.findByNickname(nickname)
				.orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));

		// 프로필 응답 생성
		return ProfileResponse.builder()
				.introduction(member.getIntroduction())
				.birthdate(member.getBirthdate())
				.build();
	}

	public void updateProfile(UpdateProfileRequest request) {
	}
}
