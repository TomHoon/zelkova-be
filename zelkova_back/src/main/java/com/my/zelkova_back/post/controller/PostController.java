package com.my.zelkova_back.post.controller;

import com.my.zelkova_back.post.dto.PostUpdateRequestDto;
import com.my.zelkova_back.post.dto.PostRequestDto;
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
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   @PostMapping("/write")
    public ResponseEntity<Post> createPost(@RequestBody PostRequestDto requestDto) {
        return ResponseEntity.ok(postService.createPost(
            requestDto.getTitle(),
            requestDto.getContent(),
            requestDto.getWriter()));
    }

    @PutMapping("/edit")
    public ResponseEntity<Post> updatePost(@RequestBody PostUpdateRequestDto dto) {
        Post updatedPost = postService.updatePost(dto);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/deleted/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
