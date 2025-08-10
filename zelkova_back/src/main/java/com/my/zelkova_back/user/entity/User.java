package com.my.zelkova_back.user.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 30)
	private String username;

	@Column(nullable = false, length = 255)
	private String password;

	@Column(nullable = false, unique = true, length = 20)
	private String phoneNumber;

	@Column(nullable = false, unique = true, length = 30)
	private String nickname;

	@Column(nullable = false, unique = true, length = 30)
	private String email;

	private LocalDate birthdate;

	@Column(length = 50)
	private String name;

	@Column(length = 20)
	private String mobileCarrier;

	@Column(length = 100)
	private String introduction;

	@Column(nullable = false, length = 20)
	@Builder.Default
	private String role = "ROLE_USER"; // 기본값

	@Column(nullable = false, length = 20)
	@Builder.Default
	private String state = "ACTIVE";

	@Column(nullable = false)
	@Builder.Default
	private LocalDateTime createdAt = LocalDateTime.now();

	private LocalDateTime updatedAt;

	private LocalDateTime withdrawnAt;

	private String socialType;

	private String socialId;

	private Long profileFileId;
}
