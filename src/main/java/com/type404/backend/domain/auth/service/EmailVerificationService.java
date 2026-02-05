package com.type404.backend.domain.auth.service;

import com.type404.backend.domain.auth.entity.EmailVerificationEntity;
import com.type404.backend.domain.auth.repository.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {
    private final EmailVerificationRepository emailVerificationRepository;
    private static final int EXPIRE_MINUTES = 5;

    // 인증 코드 발송 요청
    public void sendVerificationEmail(String email) {
        String authCode = generateAuthCode();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt = now.plusMinutes(EXPIRE_MINUTES);

        EmailVerificationEntity emailEntity =
                emailVerificationRepository.findByUserEmail(email)
                        .map(existing -> updateVerification(existing, authCode, expiresAt))
                        .orElseGet(() -> createVerification(email, authCode, expiresAt));
        emailVerificationRepository.save(emailEntity);
    }

    // 이메일이 없을 경우, 새로운 행 생성
    private EmailVerificationEntity createVerification(String email, String authCode, LocalDateTime expiresAt) {
        return EmailVerificationEntity.builder()
                .userEmail(email)
                .authCode(authCode)
                .expiresAt(expiresAt)
                .isVerified(false)
                .build();
    }

    // 이메일이 있을 경우, 기존 행 업데이트
    private EmailVerificationEntity updateVerification(EmailVerificationEntity emailEntity, String authCode, LocalDateTime expiresAt) {
        emailEntity.setAuthCode(authCode);
        emailEntity.setExpiresAt(expiresAt);
        emailEntity.setIsVerified(false);
        return emailEntity;
    }

    // 인증 코드 검증
    public boolean verifyCode(String email, String inputCode) {
        EmailVerificationEntity emailEntity = emailVerificationRepository.findByUserEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일로 전송된 인증 코드가 존재하지 않습니다."));

        // 이미 인증된 경우
        if (Boolean.TRUE.equals(emailEntity.getIsVerified())) { return true; }
        // 만료 기한 체크
        if (!emailEntity.getExpiresAt().isBefore(LocalDateTime.now())) { return false; }
        // 코드 불일치
        if (!emailEntity.getAuthCode().equals(inputCode)) { return false; }

        // 인증 성공 처리
        emailEntity.setIsVerified(true);
        emailVerificationRepository.save(emailEntity);
        return true;
    }

    // 인증 코드 생성
    private String generateAuthCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}