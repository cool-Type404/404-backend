package com.type404.backend.domain.auth.service;

import com.type404.backend.domain.auth.entity.EmailVerificationEntity;
import com.type404.backend.domain.auth.repository.EmailVerificationRepository;
import com.type404.backend.global.exception.ErrorCode;
import com.type404.backend.global.exception.exceptionType.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {
    private final EmailVerificationRepository emailVerificationRepository;
    private static final int EXPIRE_MINUTES = 5;
    private final EmailSenderService emailSenderService;

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
        emailSenderService.sendVerificationMail(email, authCode);
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
        emailEntity.reVerification(authCode, expiresAt);
        return emailEntity;
    }

    // 인증 코드 검증
    public void verifyCode(String email, String inputCode) {
        EmailVerificationEntity emailEntity = emailVerificationRepository.findByUserEmail(email)
                .orElseThrow(() -> new BadRequestException(
                        ErrorCode.DATA_NOT_EXIST,
                        "해당 이메일로 전송된 인증 코드가 존재하지 않습니다."
                )
        );
        // 이미 인증된 경우
        if (emailEntity.getIsVerified()) {
            throw new BadRequestException(
                    ErrorCode.DATA_ALREADY_EXIST,
                    "이미 인증된 이메일입니다."
            );
        }
        // 만료 기한 체크
        if (emailEntity.isExpired(LocalDateTime.now())) {
            throw new BadRequestException(
                    ErrorCode.INVALID_PARAMETER,
                    "인증 코드가 만료되었습니다."
            );
        }
        // 코드 불일치
        if (!emailEntity.getAuthCode().equals(inputCode)) {
            throw new BadRequestException(
                    ErrorCode.INVALID_PARAMETER,
                    "인증 코드가 일치하지 않습니다."
            );
        }
        // 인증 성공 처리
        emailEntity.verify();
        emailVerificationRepository.save(emailEntity);
    }

    // 인증 코드 생성
    private String generateAuthCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}