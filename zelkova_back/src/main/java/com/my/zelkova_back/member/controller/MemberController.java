package com.my.zelkova_back.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.my.zelkova_back.auth.dto.LoginRequest;
import com.my.zelkova_back.auth.dto.LoginResponse;
import com.my.zelkova_back.common.response.ApiResponse;
import com.my.zelkova_back.common.response.ResponseCode;
import com.my.zelkova_back.member.dto.FindIdRequest;
import com.my.zelkova_back.member.dto.FindPwRequest;
import com.my.zelkova_back.member.dto.JoinRequest;
import com.my.zelkova_back.member.dto.ProfileResponse;
import com.my.zelkova_back.member.dto.UpdateProfileRequest;
import com.my.zelkova_back.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	/**
	 * [회원가입]
	 * 회원가입 정보를 받아 새로운 사용자를 등록합니다.
	 * 
	 * @param request JoinRequest
	 * @return ResponseEntity<ApiResponse<String>> - 회원가입 완료 메시지
	 * 
	 * TODO:
	 * - 유효성 검증(@Valid) 적용 예정
	 * - 비밀번호 암호화 여부 확인
	 */
	@PostMapping("/join")
	public ResponseEntity<ApiResponse<String>> join(@RequestBody JoinRequest request) {
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, memberService.join(request)));
	}

	/**
	 * [로그인]
	 * 아이디와 비밀번호를 받아 로그인 처리 및 토큰을 반환합니다.
	 *
	 * @param request LoginRequest
	 * @return ResponseEntity<ApiResponse<LoginResponse>> - 토큰 정보
	 */
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, memberService.login(request)));
	}

	/**
	 * [아이디 찾기]
	 * 이름, 전화번호, 생년월일을 기반으로 가입된 사용자의 아이디(username)를 반환합니다.
	 * 
	 * @param request FindIdRequest - 이름, 전화번호, 생년월일 정보를 포함한 요청 객체
	 * @return ResponseEntity<ApiResponse<String>> - 가입된 아이디(username)
	 * 
	 * TODO:
	 * - 입력값 유효성 검사 고도화 예정
	 */
	@PostMapping("/find/id")
	public ResponseEntity<ApiResponse<String>> findId(@RequestBody FindIdRequest request) {
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, memberService.findId(request)));
	}

	/**
	 * [비밀번호 재설정 메일 발송]
	 * 아이디, 이메일 등 본인 확인 정보를 기반으로 비밀번호 재설정 이메일을 발송합니다.
	 * 
	 * @param request FindPwRequest
	 * @return ResponseEntity<ApiResponse<String>> - 이메일 발송 결과 메시지
	 */
	@PostMapping("/find/pw")
	public ResponseEntity<ApiResponse<String>> findPw(@RequestBody FindPwRequest request) {
		memberService.sendResetPasswordMail(request);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, "이메일 전송 완료"));
	}

	/**
	 * [회원 탈퇴]
	 * 로그인된 사용자의 회원 탈퇴를 처리합니다.
	 * 
	 * @param userDetails 로그인된 사용자 정보 (Spring Security 주입)
	 * @return ResponseEntity<ApiResponse<String>> - 탈퇴 완료 메시지
	 */
	@PutMapping("/withdraw")
	public ResponseEntity<ApiResponse<String>> withdraw(@AuthenticationPrincipal UserDetails userDetails) {
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, memberService.withdrawMember(userDetails)));
	}

	/**
	 * [프로필 조회]
	 * 닉네임을 기준으로 사용자의 프로필 정보를 반환합니다.
	 * 
	 * @param nickname 조회할 사용자 닉네임
	 * @return ResponseEntity<ApiResponse<ProfileResponse>> - 자기소개, 생년월일 정보 포함
	 * 
	 * TODO:
	 * - 프로필 사진 기능 확장 예정
	 */
	@GetMapping("/profile/{nickname}")
	public ResponseEntity<ApiResponse<ProfileResponse>> viewProfile(@PathVariable String nickname) {
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, memberService.getProfileByNickname(nickname)));
	}

	/**
	 * [프로필 수정]
	 * 사용자 본인의 프로필 정보를 수정합니다.
	 * 
	 * TODO:
	 * - @AuthenticationPrincipal UserDetails userDetails 추가 예정
	 * 
	 * @param request UpdateProfileRequest
	 * @return ResponseEntity<ApiResponse<String>> - 수정 완료 메시지
	 */
	@PutMapping("/profile/edit")
	public ResponseEntity<ApiResponse<String>> updateProfile(@RequestBody UpdateProfileRequest request) {
		memberService.updateProfile(request);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, "프로필 수정 완료"));
	}
}
