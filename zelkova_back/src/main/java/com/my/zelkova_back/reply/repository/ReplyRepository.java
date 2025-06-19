package com.my.zelkova_back.reply.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.zelkova_back.reply.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
	List<Reply> findByCommentIdAndIsDeletedFalseOrderByCreatedAtAsc(Long commentId);
}
