package com.my.zelkova_back.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.zelkova_back.comment.dto.CommentEditRequest;
import com.my.zelkova_back.comment.dto.CommentRequest;
import com.my.zelkova_back.comment.dto.CommentResponse;
import com.my.zelkova_back.comment.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
	
	@Autowired
	CommentService service;
	
	@PostMapping("/write")
	public ResponseEntity<?> commentwrite(@RequestBody CommentRequest req){
		service.writeComment(req);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<CommentResponse>> commentlist(@RequestParam("postId") Long postId){
		return ResponseEntity.ok(service.getCommentsByPostId(postId));
	}
	
	@PutMapping("/edit")
	public ResponseEntity<?> commentedit(@RequestBody CommentEditRequest req){
		service.editComment(req);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam("id") Long id) {
		service.deleteComment(id);
		return ResponseEntity.ok().build();
	}
	
}
