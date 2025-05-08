package com.example.demo.repository;

import com.example.demo.model.Test;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TestRepository {

    private final SqlSessionTemplate test1;

    // 조회
    public Test findById(Long id) {
        return test1.selectOne("TestMapper.findById", id);
    }

    // 전체 조회
    public List<Test> findAll() {
        return test1.selectList("TestMapper.findAll");
    }

    // 생성
    public int insert(Test test) {
        return test1.insert("TestMapper.insert", test);
    }

    // 수정
    public int update(Test test) {
        return test1.update("TestMapper.update", test);
    }

    // 삭제
    public int deleteById(Long id) {
        return test1.delete("TestMapper.deleteById", id);
    }

}
