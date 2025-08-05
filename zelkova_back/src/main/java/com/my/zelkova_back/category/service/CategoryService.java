package com.my.zelkova_back.category.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.my.zelkova_back.board.dto.BoardSimpleDto;
import com.my.zelkova_back.category.dto.CategoryBoardFlatDto;
import com.my.zelkova_back.category.dto.HeaderResponse;
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

	public List<HeaderResponse> getHeaderResponseList() {
	    List<CategoryBoardFlatDto> flatList = categoryRepository.findFlatCategoryBoards();

	    Map<String, List<BoardSimpleDto>> grouped = flatList.stream()
	        .collect(Collectors.groupingBy(
	            CategoryBoardFlatDto::getCategoryName,
	            LinkedHashMap::new,
	            Collectors.mapping(
	                dto -> new BoardSimpleDto(dto.getBoardName(), dto.getBoardUrl()),
	                Collectors.toList()
	            )
	        ));

	    return grouped.entrySet().stream()
	    	    .map((Map.Entry<String, List<BoardSimpleDto>> entry) -> 
	            new HeaderResponse(entry.getKey(), entry.getValue()))
	            .toList();
	}
}
