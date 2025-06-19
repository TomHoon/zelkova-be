package com.my.zelkova_back.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.zelkova_back.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
