package com.type404.backend.domain.auth.controller;

import com.type404.backend.domain.auth.service.UserLogoutService;
import com.type404.backend.global.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class UserLogoutController {
    private final UserLogoutService userLogoutService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal CustomUserDetails userDetails) {
        userLogoutService.logout(userDetails.getUsername());
        return ResponseEntity.ok("로그아웃이 완료되었습니다.");
    }
}
