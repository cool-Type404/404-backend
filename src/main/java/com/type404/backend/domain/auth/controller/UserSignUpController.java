package com.type404.backend.domain.auth.controller;

import com.type404.backend.domain.auth.dto.request.SignUpRequest;
import com.type404.backend.domain.auth.service.UserSignUpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
public class UserSignUpController {
    private final UserSignUpService userSignUpService;

    @Operation(summary = "회원가입", description = "이메일, 비밀번호, 닉네임 등의 정보를 입력받아 새로운 사용자를 등록합니다.")
    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(
            @Valid @RequestBody SignUpRequest request
    ) {
        userSignUpService.signUp(request);
        return ResponseEntity.ok().build();
    }
}
