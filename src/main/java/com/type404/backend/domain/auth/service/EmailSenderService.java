package com.type404.backend.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender mailSender;

    public void sendVerificationMail(String email, String authCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[홍밥] 이메일 인증을 완료해주세요.");
        message.setText("인증 코드 : " + authCode + "\n5분 후 인증 코드가 만료됩니다.\n시간 안에 이메일 인증을 완료해주세요!");
        mailSender.send(message);
    }
}
