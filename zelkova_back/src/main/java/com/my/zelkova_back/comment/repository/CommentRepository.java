package com.my.zelkova_back.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.zelkova_back.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long>{
	List<Comment> findByPostIdAndIsDeletedFalse(Long postId);
}
