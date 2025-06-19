package com.my.zelkova_back.post.service;

import com.my.zelkova_back.post.dto.PostUpdateRequestDto;
import com.my.zelkova_back.post.entity.Post;
import com.my.zelkova_back.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public Post createPost(String title, String content, String writer) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return postRepository.save(post);
    }

    public Post updatePost(PostUpdateRequestDto dto) {
        Optional<Post> optionalPost = postRepository.findById(dto.getId());

        if (optionalPost.isEmpty()) {
            throw new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다: " + dto.getId());
        }

        Post post = optionalPost.get();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
        postRepository.delete(post);
    }
}
