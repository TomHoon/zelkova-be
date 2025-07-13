package com.my.zelkova_back.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.zelkova_back.common.exception.CustomException;
import com.my.zelkova_back.common.response.ResponseCode;
import com.my.zelkova_back.member.dto.*;
import com.my.zelkova_back.member.entity.Member;
import com.my.zelkova_back.member.entity.Role;
import com.my.zelkova_back.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String join(JoinRequest request) {
        if (request.getUsername().length() < 8) {
            throw new CustomException(ResponseCode.INVALID_USERNAME);
        }

        if (!request.getEmail().matches("^[a-zA-Z0-9]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$")) {
            throw new CustomException(ResponseCode.INVALID_EMAIL_FORMAT);
        }

        if (!request.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,72}$")) {
            throw new CustomException(ResponseCode.INVALID_PASSWORD);
        }

        if (memberRepository.existsByUsername(request.getUsername())) {
            throw new CustomException(ResponseCode.DUPLICATE_USERNAME);
        }

        if (memberRepository.existsByNickname(request.getNickname())) {
            throw new CustomException(ResponseCode.DUPLICATE_NICKNAME);
        }

        if (memberRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new CustomException(ResponseCode.DUPLICATE_PHONE_NUMBER);
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Member member = Member.builder()
                .username(request.getUsername())
                .password(encodedPassword)
                .phoneNumber(request.getPhoneNumber())
                .nickname(request.getNickname())
                .email(request.getEmail())
                .birthdate(LocalDate.parse(request.getBirthdate()))
                .name(request.getName())
                .mobileCarrier(request.getMobileCarrier())
                .socialType(request.getSocialType())
                .socialId(request.getSocialId())
                .role(Role.ROLE_USER)
                .build();

        memberRepository.save(member);

        return "회원가입에 성공하였습니다!";
    }

    @Override
    public String findId(FindIdRequest request) {
        if (request.getName() == null || request.getPhoneNumber() == null || request.getBirthdate() == null) {
            throw new CustomException(ResponseCode.INVALID_INPUT);
        }

        Member member = memberRepository.findByNameAndPhoneNumberAndBirthdate(
                request.getName(),
                request.getPhoneNumber(),
                request.getBirthdate()).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));

        return member.getUsername();
    }

    @Override
    public ProfileResponse getProfileByNickname(String nickname) {
        if (nickname == null || nickname.isBlank()) {
            throw new CustomException(ResponseCode.INVALID_INPUT);
        }

        Member member = memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));

        return ProfileResponse.builder()
                .introduction(member.getIntroduction())
                .birthdate(member.getBirthdate())
                .build();
    }

    @Override
    public Object kakaoLogin(KakaoLoginRequest request) {
        try {
            // 1. 카카오 API 호출해서 사용자 정보 가져오기
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest kakaoRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://kapi.kakao.com/v2/user/me"))
                    .header("Authorization", "Bearer " + request.getAccessToken())
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(kakaoRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new CustomException(ResponseCode.UNAUTHORIZED); // 카카오 인증 실패
            }

            // 2. 응답 JSON 파싱
            ObjectMapper mapper = new ObjectMapper();
            JsonNode body = mapper.readTree(response.body());
            String kakaoId = body.get("id").asText();
            String nickname = body.get("properties").get("nickname").asText();
            String email = null;
            if (body.has("kakao_account") && body.get("kakao_account").has("email")) {
                email = body.get("kakao_account").get("email").asText();
            }

            // 3. DB에서 socialId로 회원 조회
            Optional<Member> optionalMember = memberRepository.findBySocialId(kakaoId);
            Member member;

            if (optionalMember.isPresent()) {
                member = optionalMember.get();
            } else {
                // 4. 신규 회원이면 회원가입 처리
                member = Member.builder()
                        .username("kakao_" + kakaoId)
                        .password(passwordEncoder.encode("kakao_dummy_password")) // 실제 로그인 시 비밀번호 사용 안 함
                        .nickname(nickname)
                        .email(email)
                        .role(Role.ROLE_USER)
                        .socialType("KAKAO")
                        .socialId(kakaoId)
                        .build();

                memberRepository.save(member);
            }

            // 5. 로그인 성공 처리 (예: JWT 토큰 생성 후 반환 등)
            // 지금은 단순 문자열 반환(JWT 토큰 발급 필요)
            return "카카오 로그인 성공";

        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Object login(LoginRequest request) {
        return null;
    }

    @Override
    public void sendResetPasswordMail(FindPwRequest request) {
    }

    @Override
    public void updateProfile(UpdateProfileRequest request) {
    }
}
