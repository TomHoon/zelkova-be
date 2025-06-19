package com.my.zelkova_back.category.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.my.zelkova_back.category.entity.Category;
import com.my.zelkova_back.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
