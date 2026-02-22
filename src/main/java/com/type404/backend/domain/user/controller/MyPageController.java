package com.type404.backend.domain.user.controller;

import com.type404.backend.domain.user.dto.response.MyPageResponse;
import com.type404.backend.domain.user.service.MyPageService;
import com.type404.backend.global.userdetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "user", description = "사용자 개인화 기능 관련 API")
@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @Operation(summary = "마이페이지 정보 조회", description = "현재 로그인한 사용자의 프로필, 활동 내역 등 마이페이지 정보를 조회합니다.")
    @GetMapping("/me")
    public ResponseEntity<MyPageResponse> getMyPage(@AuthenticationPrincipal CustomUserDetails userDetails) {
        MyPageResponse response = myPageService.getMyPageInfo(userDetails.getUserPK());
        return ResponseEntity.ok(response);
    }
}