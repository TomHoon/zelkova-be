package com.my.zelkova_back.comment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<ApiResponse<Void>> write(@RequestBody CommentRequest req) {
		service.writeComment(req);
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
