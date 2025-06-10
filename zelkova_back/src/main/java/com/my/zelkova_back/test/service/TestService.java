package com.my.zelkova_back.test.service;

import org.springframework.stereotype.Service;

import com.my.zelkova_back.test.dto.TestGetResponse;
import com.my.zelkova_back.test.dto.TestJoinRequest;
import com.my.zelkova_back.test.dto.TestUpdateRequest;
import com.my.zelkova_back.test.entity.Test;
import com.my.zelkova_back.test.repository.TestRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {

	private final TestRepository testRepository;

	public TestGetResponse getTestById(Long id) {
		Test test = testRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("해당 테스트 없음: id=" + id));
		
		return TestGetResponse.builder()
			    .username(test.getUsername())
			    .email(test.getEmail())
			    .build();
	}

	@Transactional
	public String insertUser(TestJoinRequest testJoinRequest) {
		Test test = Test.builder()
			.username(testJoinRequest.getUsername())
			.email(testJoinRequest.getEmail())
			.password(testJoinRequest.getPassword())
			.build();

		testRepository.save(test);

		return "성공하였습니다.";
	}

	@Transactional
	public String updateTest(TestUpdateRequest request) {
		Test test = testRepository.findById(request.getId())
				.orElseThrow(() -> new EntityNotFoundException("해당 테스트 없음: id=" + request.getId()));

		test.updatePassword(request.getPassword()); // 💡 변경 감지로 자동 UPDATE

		return "성공하였습니다.";
	}

	@Transactional
	public String deleteTest(Long id) {
		Test test = testRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("해당 테스트 없음: id=" + id));

		testRepository.delete(test);

		return "성공하였습니다.";
	}



	
}
