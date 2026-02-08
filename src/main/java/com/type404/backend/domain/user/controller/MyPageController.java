package com.type404.backend.domain.user.controller;

import com.type404.backend.domain.user.dto.response.MyPageResponse;
import com.type404.backend.domain.user.service.MyPageService;
import com.type404.backend.global.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/me")
    public ResponseEntity<MyPageResponse> getMyPage(@AuthenticationPrincipal CustomUserDetails userDetails) {
        MyPageResponse response = myPageService.getMyPageInfo(userDetails.getUserPK());
        return ResponseEntity.ok(response);
    }
}
