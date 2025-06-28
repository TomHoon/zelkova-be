package com.my.zelkova_back.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.zelkova_back.auth.token.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String>{

}
