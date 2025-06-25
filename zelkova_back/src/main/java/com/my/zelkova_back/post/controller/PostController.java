package com.my.zelkova_back.post.controller;

import com.my.zelkova_back.common.response.ApiResponse;
import com.my.zelkova_back.common.response.ResponseCode;
import com.my.zelkova_back.post.dto.PostRequestDto;
import com.my.zelkova_back.post.dto.PostUpdateRequestDto;
import com.my.zelkova_back.post.entity.Post;
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

	@GetMapping("/list")
	public ResponseEntity<ApiResponse<List<Post>>> getAllPosts() {
		List<Post> posts = postService.getAllPosts();
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, posts));
	}

	// 현재 컨트롤러에서 예외 처리 중 서비스에서 CustomException 처리로 바꿔주세요.
	@GetMapping("/detail/{id}")
	public ResponseEntity<ApiResponse<Post>> getPostById(@PathVariable Long id) {
		return postService.getPostById(id)
			.map(post -> ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, post)))
			.orElse(ResponseEntity.status(ResponseCode.NOT_FOUND.getStatus())
				.body(ApiResponse.error(ResponseCode.NOT_FOUND)));
	}

	@PostMapping("/write")
	public ResponseEntity<ApiResponse<Post>> createPost(@RequestBody PostRequestDto requestDto) {
		Post post = postService.createPost(
			requestDto.getTitle(),
			requestDto.getContent(),
			requestDto.getWriter()
		);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, post));
	}

	@PutMapping("/edit")
	public ResponseEntity<ApiResponse<Post>> updatePost(@RequestBody PostUpdateRequestDto dto) {
		Post updatedPost = postService.updatePost(dto);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, updatedPost));
	}

	@DeleteMapping("/deleted/{id}")
	public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long id) {
		postService.deletePost(id);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS));
	}
}
