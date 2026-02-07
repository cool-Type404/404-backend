package com.type404.backend.domain.auth.service;

import com.type404.backend.domain.auth.dto.request.SignUpRequest;
import com.type404.backend.domain.auth.entity.EmailVerificationEntity;
import com.type404.backend.domain.auth.entity.UserInfoEntity;
import com.type404.backend.domain.auth.repository.EmailVerificationRepository;
import com.type404.backend.domain.auth.repository.UserInfoRepository;
import com.type404.backend.global.exception.ErrorCode;
import com.type404.backend.global.exception.exceptionType.BadRequestException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserSignUpService {
    private final EmailVerificationRepository emailVerificationRepository;
    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 회원가입 요청
    @Transactional
    public void signUp(SignUpRequest request) {
        // 이메일 인증 정보 조회
        EmailVerificationEntity verification = emailVerificationRepository.findByUserEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException(
                        ErrorCode.DATA_NOT_EXIST,
                        "이메일 인증 정보가 존재하지 않습니다."
                )
        );
        // 이메일 인증 여부 확인
        if (!verification.getIsVerified()) {
            throw new BadRequestException(
                    ErrorCode.UNAUTHORIZED_ACCESS,
                    "이메일 인증이 완료되지 않았습니다."
            );
        }
        // 이미 가입된 이메일인지 확인
        if (userInfoRepository.existsByUserEmail(request.getEmail())) {
            throw new BadRequestException(
                    ErrorCode.DATA_ALREADY_EXIST,
                    "이미 가입된 이메일입니다."
            );
        }

        // 비밀번호 암호화하기
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Entity에 데이터 저장
        UserInfoEntity userEntity = UserInfoEntity.create(
            request.getEmail(),
            encodedPassword,
            request.getNickname(),
            request.getGender(),
            request.getAge(),
            request.getEatingLevel()
        );
        userInfoRepository.save(userEntity);
    }
}
