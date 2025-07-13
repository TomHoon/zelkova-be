package com.my.zelkova_back.post.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.zelkova_back.common.exception.CustomException;
import com.my.zelkova_back.common.response.ResponseCode;
import com.my.zelkova_back.member.entity.Member;
import com.my.zelkova_back.member.repository.MemberRepository;
import com.my.zelkova_back.post.dto.PostDetailResponse;
import com.my.zelkova_back.post.dto.PostEditResponse;
import com.my.zelkova_back.post.dto.PostNavResponse;
import com.my.zelkova_back.post.dto.PostRequest;
import com.my.zelkova_back.post.dto.PostResponse;
import com.my.zelkova_back.post.dto.PostUpdateRequest;
import com.my.zelkova_back.post.entity.Post;
import com.my.zelkova_back.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

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

    public PostDetailResponse createPost(PostRequest request,String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));

        Post post = Post.builder()
                .boardId(request.getBoardId())
                .member(member)
                .title(request.getTitle())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        return PostDetailResponse.from(postRepository.save(post));
    }
    @Transactional
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
                .writerName(post.getMember().getNickname())
                .createdAt(post.getCreatedAt())
                .viewCount(post.getViewCount())
                .boardId(post.getBoardId())
                .build();
    }
    
    public List<PostNavResponse> getPostNavigation(Long postId) {
        Optional<Post> prev = postRepository.findTopByIdLessThanOrderByIdDesc(postId);
        Optional<Post> next = postRepository.findTopByIdGreaterThanOrderByIdAsc(postId);

        List<PostNavResponse> result = new ArrayList<>();
        prev.ifPresent(p -> result.add(PostNavResponse.from(p)));
        next.ifPresent(p -> result.add(PostNavResponse.from(p)));

        return result;
    }
}
