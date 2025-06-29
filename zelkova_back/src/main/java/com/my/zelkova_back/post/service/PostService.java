package com.my.zelkova_back.post.service;

import com.my.zelkova_back.common.exception.CustomException;
import com.my.zelkova_back.common.response.ResponseCode;
import com.my.zelkova_back.post.dto.*;
import com.my.zelkova_back.post.entity.Post;
import com.my.zelkova_back.post.repository.PostRepository;
import com.my.zelkova_back.user.entity.User;
import com.my.zelkova_back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PostDetailResponse getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ResponseCode.NOT_FOUND));
        return PostDetailResponse.from(post);
    }

    public PostDetailResponse createPost(PostRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));

        Post post = Post.builder()
                .boardId(request.getBoardId())
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        return PostDetailResponse.from(postRepository.save(post));
    }

    public PostEditResponse updatePost(PostUpdateRequest request) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new CustomException(ResponseCode.NOT_FOUND));

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUpdatedAt(LocalDateTime.now());

        postRepository.save(post); // dirty checking으로 생략 가능

        return PostEditResponse.from(post);
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ResponseCode.NOT_FOUND));

        postRepository.delete(post);
    }

    private PostResponse toResponse(Post post) {
        return PostResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .writerName(post.getUser().getNickname())
                .createdAt(post.getCreatedAt())
                .viewCount(post.getViewCount())
                .build();
    }
}
