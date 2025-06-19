package com.my.zelkova_back.reply.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.zelkova_back.reply.dto.ReplyEditRequest;
import com.my.zelkova_back.reply.dto.ReplyRequest;
import com.my.zelkova_back.reply.dto.ReplyResponse;
import com.my.zelkova_back.reply.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reply")
@RequiredArgsConstructor
public class ReplyController {

	private final ReplyService replyService;

	@GetMapping("/list")
	public ResponseEntity<List<ReplyResponse>> list(@RequestParam("commentId") Long commentId) {
		return ResponseEntity.ok(replyService.getRepliesByCommentId(commentId));
	}

	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody ReplyRequest req) {
		replyService.writeReply(req);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/edit")
	public ResponseEntity<?> edit(@RequestBody ReplyEditRequest req) {
		replyService.editReply(req);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam("id") Long id) {
		replyService.deleteReply(id);
		return ResponseEntity.ok().build();
	}
}
