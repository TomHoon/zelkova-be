package com.my.zelkova_back.reply.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.zelkova_back.reply.dto.ReplyEditRequest;
import com.my.zelkova_back.reply.dto.ReplyRequest;
import com.my.zelkova_back.reply.dto.ReplyResponse;
import com.my.zelkova_back.reply.entity.Reply;

public interface ReplyService{
	void writeReply(ReplyRequest req);
	List<ReplyResponse> getRepliesByCommentId(Long commentId);
	void editReply(ReplyEditRequest request);
	void deleteReply(Long id);
}
