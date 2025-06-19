package com.my.zelkova_back.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.zelkova_back.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
