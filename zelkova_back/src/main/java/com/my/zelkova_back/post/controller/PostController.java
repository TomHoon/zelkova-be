package com.my.zelkova_back.post.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.zelkova_back.common.response.ApiResponse;
import com.my.zelkova_back.common.response.ResponseCode;
import com.my.zelkova_back.post.dto.PostDetailResponse;
import com.my.zelkova_back.post.dto.PostEditResponse;
import com.my.zelkova_back.post.dto.PostNavResponse;
import com.my.zelkova_back.post.dto.PostRequest;
import com.my.zelkova_back.post.dto.PostResponse;
import com.my.zelkova_back.post.dto.PostUpdateRequest;
import com.my.zelkova_back.post.service.PostService;

import lombok.RequiredArgsConstructor;

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
	public ResponseEntity<ApiResponse<PostDetailResponse>> getPostById(@PathVariable(name="postId") Long postId) {
		PostDetailResponse post = postService.getPostById(postId);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, post));
	}

	// 게시글 작성
	@PostMapping("/write")
	public ResponseEntity<ApiResponse<PostDetailResponse>> createPost(@AuthenticationPrincipal UserDetails userDetails,@RequestBody PostRequest request) {
		String username = userDetails.getUsername();
		PostDetailResponse post = postService.createPost(request,username);
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
	
	// 이전, 다음글 
	@GetMapping("/nav/{postId}")
	public ResponseEntity<ApiResponse<List<PostNavResponse>>> getPostNavigation(@PathVariable("postId") Long postId) {
	    List<PostNavResponse> navPosts = postService.getPostNavigation(postId);
	    return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, navPosts));
	}
}
