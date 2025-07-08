package com.my.zelkova_back.comment.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.my.zelkova_back.comment.dto.CommentEditRequest;
import com.my.zelkova_back.comment.dto.CommentRequest;
import com.my.zelkova_back.comment.dto.CommentResponse;
import com.my.zelkova_back.comment.entity.Comment;
import com.my.zelkova_back.comment.repository.CommentRepository;
import com.my.zelkova_back.member.entity.Member;
import com.my.zelkova_back.member.repository.MemberRepository;
import com.my.zelkova_back.post.entity.Post;
import com.my.zelkova_back.post.repository.PostRepository;
import com.my.zelkova_back.user.entity.User;
import com.my.zelkova_back.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	
	@Override
	public void writeComment(CommentRequest req) {
		Post post=postRepository.findById(req.getPostId()).orElseThrow();
		Member member=memberRepository.findById(req.getUserId()).orElseThrow();
		
		Comment comment = Comment.builder()
				.post(post)
				.member(member)
				.content(req.getContent())
				.createdAt(LocalDateTime.now())
				.isDeleted(false)
				.build();
		
		commentRepository.save(comment);
	}

	@Override
	public List<CommentResponse> getCommentsByPostId(Long postId) {
		return commentRepository.findByPostIdAndIsDeletedFalse(postId)
				.stream()
				.map(CommentResponse::from)
				.collect(Collectors.toList());
	}

	@Override
	public void editComment(CommentEditRequest request) {
		Comment comment = commentRepository.findById(request.getId())
				.orElseThrow(() -> new IllegalArgumentException("댓글 없음"));
		comment.setContent(request.getContent());
		comment.setUpdatedAt(LocalDateTime.now());
		commentRepository.save(comment);
	}

	@Override
	public void deleteComment(Long id) {
		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("댓글 없음"));
		comment.setIsDeleted(true);
		comment.setDeletedAt(LocalDateTime.now());
		commentRepository.save(comment);
	}

}
