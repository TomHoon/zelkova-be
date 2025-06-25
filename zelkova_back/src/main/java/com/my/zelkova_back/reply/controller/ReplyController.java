package com.my.zelkova_back.reply.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.my.zelkova_back.common.response.ApiResponse;
import com.my.zelkova_back.common.response.ResponseCode;
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
	public ResponseEntity<ApiResponse<List<ReplyResponse>>> list(@RequestParam("commentId") Long commentId) {
		List<ReplyResponse> replies = replyService.getRepliesByCommentId(commentId);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, replies));
	}

	@PostMapping("/write")
	public ResponseEntity<ApiResponse<Void>> write(@RequestBody ReplyRequest req) {
		replyService.writeReply(req);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS));
	}

	@PutMapping("/edit")
	public ResponseEntity<ApiResponse<Void>> edit(@RequestBody ReplyEditRequest req) {
		replyService.editReply(req);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ApiResponse<Void>> delete(@RequestParam("id") Long id) {
		replyService.deleteReply(id);
		return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS));
	}
}
