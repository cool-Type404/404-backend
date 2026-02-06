package com.type404.backend.domain.auth.controller;

import com.type404.backend.domain.auth.dto.request.EmailSendRequest;
import com.type404.backend.domain.auth.dto.request.EmailVerifyRequest;
import com.type404.backend.domain.auth.service.EmailVerificationService;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/email-verification")
@RequiredArgsConstructor
public class EmailVerificationController {
    private final EmailVerificationService emailVerificationService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendEmail(
            @RequestBody EmailSendRequest request
    ) {
        emailVerificationService.sendVerificationEmail(request.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify")
    public ResponseEntity<Void> verifyEmail(
            @RequestBody EmailVerifyRequest request
    ) {
        emailVerificationService.verifyCode(
                request.getEmail(),
                request.getAuthCode()
        );
        return ResponseEntity.ok().build();
    }
}
