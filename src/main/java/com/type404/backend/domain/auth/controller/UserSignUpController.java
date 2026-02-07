package com.type404.backend.domain.auth.controller;

import com.type404.backend.domain.auth.dto.request.SignUpRequest;
import com.type404.backend.domain.auth.service.UserSignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class UserSignUpController {
    private final UserSignUpService userSignUpService;

    @PostMapping("/signup")
    public ResponseEntity<Void> sighUp(
            @Valid @RequestBody SignUpRequest request
    ) {
        userSignUpService.signUp(request);
        return ResponseEntity.ok().build();
    }
}
