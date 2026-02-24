package com.type404.backend.domain.auth.controller;

import com.type404.backend.domain.auth.dto.request.LoginRequest;
import com.type404.backend.domain.auth.dto.response.LoginResponse;
import com.type404.backend.domain.auth.service.UserLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "사용자 인증 관련 API")
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class UserLoginController {
    private final UserLoginService userLoginService;

    @Operation(summary = "일반 로그인", description = "이메일과 비밀번호를 이용해 로그인을 진행하고, JWT 액세스 토큰 및 리프레시 토큰을 발급합니다.")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {
        LoginResponse response = userLoginService.login(request);
        return ResponseEntity.ok(response);
    }
}