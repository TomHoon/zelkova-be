package com.my.zelkova_back.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.my.zelkova_back.board.entity.Board;
import com.my.zelkova_back.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

	public List<Board> getHeaderList() {
		// TODO Auto-generated method stub
		return null;
	}
}