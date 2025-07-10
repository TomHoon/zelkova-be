package com.my.zelkova_back.member.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 30)
	private String username;

	@Column(nullable = false, length = 255)
	private String password;

	@Column(nullable = false, unique = true, length = 20)
	private String phoneNumber;

	@Column(nullable = false, unique = true, length = 20)
	private String nickname;

	@Column(length = 30)
	private String email;

	private LocalDate birthdate;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 50)
	private String mobileCarrier;

	@Column(length = 100)
	private String introduction;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	@Builder.Default
	private Role role = Role.ROLE_USER;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	@Builder.Default
	private State state = State.ACTIVE;

	@Column(nullable = false)
	@Builder.Default
	private LocalDateTime createdAt = LocalDateTime.now();

	private LocalDateTime updatedAt;

	private LocalDateTime withdrawnAt;

	@Column(length = 10)
	private String socialType;

	@Column(length = 100)
	private String socialId;

	// FK 연결 예정
	private Long profileFileId;
	
	
	public void withdraw() {
		this.state = State.WITHDRAWN;
	}
}
