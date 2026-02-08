package com.type404.backend.domain.user.dto.response;

import com.type404.backend.domain.auth.entity.enumtype.Age;
import com.type404.backend.domain.auth.entity.enumtype.EatingLevel;
import com.type404.backend.domain.auth.entity.enumtype.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MyPageResponse {
    private String email;
    private String nickname;
    private Gender gender;
    private Age age;
    private String profileImg;
    private EatingLevel eatingLevel;
}