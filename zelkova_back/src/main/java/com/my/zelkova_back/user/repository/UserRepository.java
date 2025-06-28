package com.my.zelkova_back.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.zelkova_back.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
