package com.my.zelkova_back.category.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.zelkova_back.category.entity.Category;
import com.my.zelkova_back.category.service.CategoryService;
import com.my.zelkova_back.common.response.ApiResponse;
import com.my.zelkova_back.common.response.ResponseCode;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<Category>>> list() {
    	return ResponseEntity.ok(ApiResponse.success(ResponseCode.SUCCESS, categoryService.getAllCategories()));
    }
}