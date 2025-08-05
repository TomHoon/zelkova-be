package com.my.zelkova_back.comment.service;

import java.util.List;

import com.my.zelkova_back.comment.dto.CommentEditRequest;
import com.my.zelkova_back.comment.dto.CommentRequest;
import com.my.zelkova_back.comment.dto.CommentResponse;

public interface CommentService {
	void writeComment(CommentRequest req,String username);
	List<CommentResponse> getCommentsByPostId(Long postId);
	void editComment(CommentEditRequest req);
	void deleteComment(Long id);
}
