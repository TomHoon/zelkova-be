package com.my.zelkova_back.member.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.zelkova_back.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

	boolean existsByUsername(String username);
	boolean existsByNickname(String nickname);
	boolean existsByPhoneNumber(String phoneNumber);
	Optional<Member> findByNameAndPhoneNumberAndBirthdate(String name, String phoneNumber, LocalDate birthdate);
	Optional<Member> findByNickname(String nickname);
	Optional<Member> findByUsername(String username);

}
