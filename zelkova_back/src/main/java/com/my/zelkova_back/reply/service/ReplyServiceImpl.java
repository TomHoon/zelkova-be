package com.my.zelkova_back.reply.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.zelkova_back.comment.repository.CommentRepository;
import com.my.zelkova_back.reply.dto.ReplyEditRequest;
import com.my.zelkova_back.reply.dto.ReplyRequest;
import com.my.zelkova_back.reply.dto.ReplyResponse;
import com.my.zelkova_back.reply.entity.Reply;
import com.my.zelkova_back.reply.repository.ReplyRepository;
import com.my.zelkova_back.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	private final ReplyRepository replyRepository;
	private final UserRepository userRepository;
	private final CommentRepository commentRepository;

	@Override
	public void writeReply(ReplyRequest request) {
		Reply reply = Reply.builder()
				.comment(commentRepository.findById(request.getCommentId()).orElseThrow())
				.user(userRepository.findById(request.getUserId()).orElseThrow())
				.content(request.getContent())
				.createdAt(LocalDateTime.now())
				.isDeleted(false)
				.build();
		replyRepository.save(reply);
	}

	@Override
	public List<ReplyResponse> getRepliesByCommentId(Long commentId) {
		return replyRepository.findByCommentIdAndIsDeletedFalseOrderByCreatedAtAsc(commentId)
				.stream()
				.map(ReplyResponse::from)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void editReply(ReplyEditRequest request) {
		Reply reply = replyRepository.findById(request.getId()).orElseThrow();
		reply.setContent(request.getContent());
		reply.setUpdatedAt(LocalDateTime.now());
	}

	@Override
	@Transactional
	public void deleteReply(Long id) {
		Reply reply = replyRepository.findById(id).orElseThrow();
		reply.setDeletedAt(LocalDateTime.now());
		reply.setIsDeleted(true);
	}
}
