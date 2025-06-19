package com.my.zelkova_back.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.zelkova_back.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {}