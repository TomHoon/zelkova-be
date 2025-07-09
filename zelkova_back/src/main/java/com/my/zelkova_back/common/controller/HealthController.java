package com.my.zelkova_back.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // restapi 리턴
public class HealthController {

    @GetMapping("/health") // /health 주소로 get요청 메서드실행
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("alive"); // 상태코드 200 + alive 리턴
    }
}