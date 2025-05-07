package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.TestDTO;
import com.example.demo.model.Test;
import com.example.demo.repository.TestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {
    
    private final TestRepository testRepository;

    // 조회
    public Test getTest(Long id) {
        Test dto = testRepository.findById(id);
        if (dto == null) {
            throw new IllegalArgumentException("해당 id가 없습니다." + id);
        }
        return dto;
    }

    // 전체 조회
    public List<TestDTO> getAllTests() {
        List<Test> testList = testRepository.findAll();
        return testList.stream()
                .map(test -> {
                    TestDTO dto = new TestDTO();
                    dto.setTestId(test.getTestId());
                    dto.setFirstName(test.getFirstName());
                    dto.setLastName(test.getLastName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // 생성
    // TestDTO를 받아 생성
    public Long createTest(TestDTO dto) {
        Test test = new Test();
        test.setFirstName(dto.getFirstName());
        test.setLastName(dto.getLastName());
        testRepository.insert(test);

        return test.getTestId();
    }

    // 수정
    public Long updateTest(Long id, TestDTO dto) {
        Test test = testRepository.findById(id);
        if (test == null) {
            throw new IllegalArgumentException("수정할 id가 없습니다." + id);
        }
        test.setFirstName(dto.getFirstName());
        test.setLastName(dto.getLastName());
        testRepository.update(test);
        return test.getTestId();
    }

    // 삭제
    public void deleteTest(Long id) {
        int result = testRepository.deleteById(id);
        if (result == 0) {
            throw new IllegalArgumentException("삭제할 id가 없습니다." + id);
        }
    }
}