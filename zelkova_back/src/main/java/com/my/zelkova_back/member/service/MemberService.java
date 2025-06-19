package com.my.zelkova_back.member.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.my.zelkova_back.common.exception.InvalidInputException;
import com.my.zelkova_back.member.dto.FindIdRequest;
import com.my.zelkova_back.member.dto.FindPwRequest;
import com.my.zelkova_back.member.dto.JoinRequest;
import com.my.zelkova_back.member.dto.LoginRequest;
import com.my.zelkova_back.member.dto.UpdateProfileRequest;
import com.my.zelkova_back.member.entity.Member;
import com.my.zelkova_back.member.exception.DuplicateUsernameException;
import com.my.zelkova_back.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	
	//시큐리티 추가 후 추가예정
	//private final PasswordEncoder passwordEncoder;

	public String join(JoinRequest request) {
		
		//아이디 자릿수 검사
		if (request.getUsername().length() < 8) {
			throw new InvalidInputException("아이디의 자리수가 모자랍니다.");
		}

		//이메일 유효성 검사
		if (!request.getEmail().matches("^[a-zA-Z0-9]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$")) {
			throw new InvalidInputException("이메일 형식을 올바르게 작성하세요.");
		}
		
		if (!request.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,72}$")) {
			throw new InvalidInputException("올바른 비밀번호 형식으로 작성해주세요. (8~72자사이로 영어,숫자,특수문자를 1글자 이상 사용해주세요.)");
		}

		// 중복 username 검사
		if (memberRepository.existsByUsername(request.getUsername())) {
			throw new DuplicateUsernameException("이미 존재하는 아이디입니다.");
		}
		
		// 중복 닉네임 검사
		if (memberRepository.existsByNickname(request.getNickname())) {
			throw new DuplicateUsernameException("이미 존재하는 닉네임입니다.");
		}

		// 중복 전화번호 검사
		if (memberRepository.existsByPhoneNumber(request.getPhoneNumber())) {
			throw new DuplicateUsernameException("이미 존재하는 전화번호 입니다.");
		}
		

		// 비밀번호 암호화
		//String encodedPassword = passwordEncoder.encode(request.getPassword());

		// User 객체 생성
		Member member = Member.builder()
				.username(request.getUsername())
				//.password(encodedPassword)
				.password(request.getPassword())
				.phoneNumber(request.getPhoneNumber())
				.nickname(request.getNickname())
				.email(request.getEmail())
				.birthdate(LocalDate.parse(request.getBirthdate()))
				.name(request.getName())
				.mobileCarrier(request.getMobileCarrier())
				.socialType(request.getSocialType())
				.socialId(request.getSocialId())
				.build();

		memberRepository.save(member);
		
		return "회원가입에 성공하였습니다!";
	}

	public Object login(LoginRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findId(FindIdRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public void sendResetPasswordMail(FindPwRequest request) {
		// TODO Auto-generated method stub
		
	}

	public Object getProfileById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateProfile(UpdateProfileRequest request) {
		// TODO Auto-generated method stub
		
	}
	
}
