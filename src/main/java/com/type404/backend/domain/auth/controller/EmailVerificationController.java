package com.type404.backend.domain.auth.controller;

import com.type404.backend.domain.auth.dto.request.EmailSendRequest;
import com.type404.backend.domain.auth.dto.request.EmailVerifyRequest;
import com.type404.backend.domain.auth.service.EmailVerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "auth", description = "사용자 인증 관련 API")
@RestController
@RequestMapping("api/auth/email-verification")
@RequiredArgsConstructor
public class EmailVerificationController {
    private final EmailVerificationService emailVerificationService;

    @Operation(summary = "인증 이메일 전송", description = "사용자가 입력한 이메일로 6자리 인증 코드를 전송합니다.")
    @PostMapping("/send")
    public ResponseEntity<Void> sendEmail(
            @Valid @RequestBody EmailSendRequest request
    ) {
        emailVerificationService.sendVerificationEmail(request.getEmail());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "인증 코드 확인", description = "이메일로 받은 인증 코드가 일치하는지 확인합니다.")
    @PostMapping("/verify")
    public ResponseEntity<Void> verifyEmail(
            @Valid @RequestBody EmailVerifyRequest request
    ) {
        emailVerificationService.verifyCode(
                request.getEmail(),
                request.getAuthCode()
        );
        return ResponseEntity.ok().build();
    }
}