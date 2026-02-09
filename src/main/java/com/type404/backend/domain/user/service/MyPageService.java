package com.type404.backend.domain.user.service;

import com.type404.backend.domain.auth.entity.UserInfoEntity;
import com.type404.backend.domain.auth.repository.UserInfoRepository;
import com.type404.backend.domain.user.dto.response.MyPageResponse;
import com.type404.backend.global.exception.ErrorCode;
import com.type404.backend.global.exception.exceptionType.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserInfoRepository userInfoRepository;

    // 마이페이지 조회
    public MyPageResponse getMyPageInfo(Long userPK) {
        UserInfoEntity userEntity = userInfoRepository.findById(userPK)
                .orElseThrow(() -> new BadRequestException(
                        ErrorCode.DATA_NOT_EXIST,
                        "해당 사용자가 존재하지 않습니다."
                )
        );
        return MyPageResponse.builder()
                .email(userEntity.getUserEmail())
                .nickname(userEntity.getUserNickname())
                .profileImg(userEntity.getUserImg())
                .gender(userEntity.getUserGender())
                .age(userEntity.getUserAge())
                .eatingLevel(userEntity.getEatingLevel())
                .build();
    }
}
