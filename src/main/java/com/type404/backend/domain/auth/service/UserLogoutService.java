package com.type404.backend.domain.auth.service;

import com.type404.backend.domain.auth.entity.RefreshTokenEntity;
import com.type404.backend.domain.auth.repository.RefreshTokenRepository;
import com.type404.backend.domain.auth.repository.UserInfoRepository;
import com.type404.backend.global.exception.ErrorCode;
import com.type404.backend.global.exception.exceptionType.BadRequestException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserLogoutService {
    private final RefreshTokenRepository refreshTokenRepository;

    // 로그아웃 요청
    public void logout(String userEmail) {
        RefreshTokenEntity refreshToken = refreshTokenRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new BadRequestException(
                        ErrorCode.DATA_NOT_EXIST,
                        "해당 이메일로 가입된 계정이 존재하지 않습니다."
                )
        );
        refreshTokenRepository.deleteByUserEmail(userEmail);
    }
}
