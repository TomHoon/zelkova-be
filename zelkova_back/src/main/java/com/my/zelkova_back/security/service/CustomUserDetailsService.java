package com.my.zelkova_back.security.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.my.zelkova_back.common.exception.CustomException;
import com.my.zelkova_back.common.response.ResponseCode;
import com.my.zelkova_back.member.entity.Member;
import com.my.zelkova_back.member.entity.State;
import com.my.zelkova_back.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByUsername(username)
			.orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));

		// 비활성화된 계정 체크 예시
		if (member.getState() != State.ACTIVE) {
			throw new CustomException(ResponseCode.FORBIDDEN);
		}

		// 스프링 시큐리티 전용 User 객체로 반환
		return User.builder()
				.username(member.getUsername())
				.password(member.getPassword())
				.authorities(new SimpleGrantedAuthority(member.getRole().name()))
				.build();
	}
}

