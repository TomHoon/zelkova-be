package com.my.zelkova_back.category.dto;

import java.util.List;

import com.my.zelkova_back.board.dto.BoardSimpleDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HeaderResponse {
    private String categoryName;
    private List<BoardSimpleDto> boards;
}
