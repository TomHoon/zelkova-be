package com.my.zelkova_back.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.my.zelkova_back.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}

