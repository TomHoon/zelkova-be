package com.my.zelkova_back.auth.token;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

	@Id
	private String username;

	@Column(nullable = false)
	private String token;
}