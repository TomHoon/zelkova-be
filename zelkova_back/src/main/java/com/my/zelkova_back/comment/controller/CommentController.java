package com.my.zelkova_back.comment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.zelkova_back.comment.dto.CommentEditRequest;
import com.my.zelkova_back.comment.dto.CommentRequest;
import com.my.zelkova_back.comment.dto.CommentResponse;
import com.my.zelkova_back.comment.service.CommentService;
import com.my.zelkova_back.common.response.ApiResponse;
import com.my.zelkova_back.common.response.ResponseCode;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService service;

	@PostMapping("/write")
	public ResponseEntity<ApiResponse<Void>> write(@RequestBody CommentRequest req, @AuthenticationPrincipal UserDetails userDetails) {
		service.writeComment(req, userDetails.getUsername());
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS));
	}

	@GetMapping("/list")
	public ResponseEntity<ApiResponse<List<CommentResponse>>> list(@RequestParam("postId") Long postId) {
		List<CommentResponse> comments = service.getCommentsByPostId(postId);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, comments));
	}

	@PutMapping("/edit")
	public ResponseEntity<ApiResponse<Void>> edit(@RequestBody CommentEditRequest req) {
		service.editComment(req);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ApiResponse<Void>> delete(@RequestParam("id") Long id) {
		service.deleteComment(id);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS));
	}
}
