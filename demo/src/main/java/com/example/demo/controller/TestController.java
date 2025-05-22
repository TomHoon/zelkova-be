package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TestDTO;
import com.example.demo.model.Test;
import com.example.demo.service.TestService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

     @GetMapping("/{id}") // 조회
     public Test getTest(@PathVariable("id") Long id) {
         return testService.getTest(id);
     }

     @GetMapping // 전체 조회
     public List<TestDTO> getAllTests() {
        return testService.getAllTests();
     }

     @PostMapping // 생성
     public Long createTest(@RequestBody TestDTO dto) {
         return testService.createTest(dto);
     }

     @PutMapping("/{id}") // 수정
     public Long updateTest(@PathVariable("id") Long id, @RequestBody TestDTO dto) {
         return testService.updateTest(id, dto);
     }

     @DeleteMapping("/{id}") // 삭제
     public void deleteTest(@PathVariable("id") Long id) {
         testService.deleteTest(id);
     }
}
