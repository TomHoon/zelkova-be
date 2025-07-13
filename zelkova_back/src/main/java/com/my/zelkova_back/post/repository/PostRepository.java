package com.my.zelkova_back.post.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.zelkova_back.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findTopByIdLessThanOrderByIdDesc(Long id);  // 이전 글
	Optional<Post> findTopByIdGreaterThanOrderByIdAsc(Long id); // 다음 글
}
