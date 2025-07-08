package com.my.zelkova_back.reply.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import com.my.zelkova_back.comment.entity.Comment;
import com.my.zelkova_back.member.entity.Member;
import com.my.zelkova_back.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_id", nullable = false)
	private Comment comment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;
	
	@Column(nullable=false, columnDefinition ="TEXT")
	private String content;
	
	@Column(nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	private LocalDateTime updatedAt;
	
	private LocalDateTime deletedAt;
	
	@Column(nullable = false)
	private Boolean isDeleted = false;
	
	
}
