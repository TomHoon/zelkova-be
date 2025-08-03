package com.my.zelkova_back.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.my.zelkova_back.category.dto.CategoryBoardFlatDto;
import com.my.zelkova_back.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("""
		    SELECT c.name AS categoryName, b.name AS boardName, b.url AS boardUrl
		    FROM Category c
		    JOIN Board b ON b.categoryId = c
		""")
	List<CategoryBoardFlatDto> findFlatCategoryBoards();
}
