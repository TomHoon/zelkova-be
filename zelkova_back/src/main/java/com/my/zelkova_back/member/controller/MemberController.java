package com.my.zelkova_back.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.zelkova_back.member.dto.FindIdRequest;
import com.my.zelkova_back.member.dto.FindPwRequest;
import com.my.zelkova_back.member.dto.JoinRequest;
import com.my.zelkova_back.member.dto.LoginRequest;
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
	 * @return ResponseEntity<String>
	 * 
	 * TODO:
	 * - 유효성 검증(@Valid) 적용 예정
	 * - 비밀번호 암호화 여부 확인
	 */
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody JoinRequest request) {
		return ResponseEntity.ok(memberService.join(request));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(memberService.login(request));
	}

	@PostMapping("/find/id")
	public ResponseEntity<?> findId(@RequestBody FindIdRequest request) {
		return ResponseEntity.ok(memberService.findId(request));
	}

	@PostMapping("/find/pw")
	public ResponseEntity<?> findPw(@RequestBody FindPwRequest request) {
		memberService.sendResetPasswordMail(request);
		return ResponseEntity.ok("이메일 전송 완료");
	}

	@PutMapping("/withdraw")
	public ResponseEntity<?> withdraw() {
		//받는 값에 @AuthenticationPrincipal UserDetails userDetails 추가예정
		return ResponseEntity.ok("회원 탈퇴 처리 완료");
	}

	@GetMapping("/profile/{id}")
	public ResponseEntity<?> viewProfile(@PathVariable Long id) {
		return ResponseEntity.ok(memberService.getProfileById(id));
	}

	@PutMapping("/profile")
	public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request){
		//받는 값에 @AuthenticationPrincipal UserDetails userDetails 추가예정
		memberService.updateProfile(request);
		return ResponseEntity.ok("프로필 수정 완료");
	}
}

