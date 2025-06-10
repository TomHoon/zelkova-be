package com.my.zelkova_back.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.zelkova_back.test.dto.TestGetResponse;
import com.my.zelkova_back.test.dto.TestJoinRequest;
import com.my.zelkova_back.test.dto.TestUpdateRequest;
import com.my.zelkova_back.test.entity.Test;
import com.my.zelkova_back.test.service.TestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {
	
	private final TestService testService;
	
	@GetMapping("/view/{id}")
	public ResponseEntity<TestGetResponse> getTest(@PathVariable("id") Long id){
		
		return ResponseEntity.ok().body(testService.getTestById(id));
	}
	
	@PostMapping("/join")
	public ResponseEntity<String> postTest(@RequestBody TestJoinRequest testJoinRequest){
		
		return ResponseEntity.ok().body(testService.insertUser(testJoinRequest));
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> putTest(@RequestBody TestUpdateRequest testUpdateRequest) {
		
		return ResponseEntity.ok().body(testService.updateTest(testUpdateRequest));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTest(@PathVariable("id") Long id){
		
		return ResponseEntity.ok().body(testService.deleteTest(id));
	}
	
	
}
