package com.my.zelkova_back.post.entity;

import com.my.zelkova_back.member.entity.Member;
import com.my.zelkova_back.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "board_id", nullable = false)
	private Long boardId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob
	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	private LocalDateTime updatedAt;

	private LocalDateTime deletedAt;

	@Builder.Default
	@Column(nullable = false)
	private Boolean isDeleted = false;

	private Integer viewCount = 0;
}
