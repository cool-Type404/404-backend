package com.type404.backend.domain.auth.controller;

import com.type404.backend.domain.auth.dto.request.LoginRequest;
import com.type404.backend.domain.auth.dto.response.LoginResponse;
import com.type404.backend.domain.auth.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class UserLoginController {
    private final UserLoginService userLoginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {
        LoginResponse response = userLoginService.login(request);
        return ResponseEntity.ok(response);
    }
}
