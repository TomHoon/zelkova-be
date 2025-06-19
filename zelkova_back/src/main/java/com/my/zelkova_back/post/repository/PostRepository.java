package com.my.zelkova_back.post.repository;

import com.my.zelkova_back.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}