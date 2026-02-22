package com.type404.backend.domain.auth.controller;

import com.type404.backend.domain.auth.service.UserLogoutService;
import com.type404.backend.global.userdetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "auth", description = "사용자 인증 관련 API")
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class UserLogoutController {
    private final UserLogoutService userLogoutService;

    @Operation(summary = "로그아웃", description = "현재 사용자의 세션을 종료하고, 발급된 JWT 토큰을 무효화 처리합니다.")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        userLogoutService.logout(userDetails.getUsername());
        return ResponseEntity.ok("로그아웃이 완료되었습니다.");
    }
}
