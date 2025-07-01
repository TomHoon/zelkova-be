package com.my.zelkova_back.post.controller;

import com.my.zelkova_back.common.response.ApiResponse;
import com.my.zelkova_back.common.response.ResponseCode;
import com.my.zelkova_back.post.dto.*;
import com.my.zelkova_back.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	// 게시글 목록 조회
	@GetMapping("/list")
	public ResponseEntity<ApiResponse<List<PostResponse>>> getAllPosts() {
		List<PostResponse> posts = postService.getAllPosts();
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, posts));
	}

	// 게시글 상세 조회
	@GetMapping("/detail/{postId}")
	public ResponseEntity<ApiResponse<PostDetailResponse>> getPostById(@PathVariable Long postId) {
		PostDetailResponse post = postService.getPostById(postId);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, post));
	}

	// 게시글 작성
	@PostMapping("/write")
	public ResponseEntity<ApiResponse<PostDetailResponse>> createPost(@RequestBody PostRequest request) {
		PostDetailResponse post = postService.createPost(request);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, post));
	}

	// 게시글 수정
	@PutMapping("/edit")
	public ResponseEntity<ApiResponse<PostEditResponse>> updatePost(@RequestBody PostUpdateRequest request) {
		PostEditResponse updatedPost = postService.updatePost(request);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, updatedPost));
	}

	// 게시글 삭제
	@DeleteMapping("/deleted/{postId}")
	public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long postId) {
		postService.deletePost(postId);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS));
	}
}
