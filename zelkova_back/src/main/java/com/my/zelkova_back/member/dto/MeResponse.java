package com.my.zelkova_back.member.dto;

import com.my.zelkova_back.member.entity.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MeResponse {
    private String nickname;

    public static MeResponse from(Member member) {
        return MeResponse.builder()
                .nickname(member.getNickname())
                .build();
    }
}