package com.type404.backend.domain.user.controller;

import com.type404.backend.domain.user.dto.request.StoreAddRequestDTO;
import com.type404.backend.domain.user.service.StoreAddService;
import com.type404.backend.global.exception.ErrorCode;
import com.type404.backend.global.exception.exceptionType.BadRequestException;
import com.type404.backend.global.userdetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "user", description = "사용자 개인화 기능 관련 API")
@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class StoreAddController {
    private final StoreAddService storeAddService;

    @Operation(summary = "식당 추가 요청", description = "사용자가 새로운 식당 정보를 시스템에 등록해달라고 요청합니다.")
    @PostMapping("/store-addition")
    public ResponseEntity<String> requestStoreAddition(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody StoreAddRequestDTO request
    ) {
        if (userDetails == null) {
            throw new BadRequestException(ErrorCode.UNAUTHORIZED, "로그인이 필요한 서비스입니다.");
        }
        storeAddService.requestStoreAddition(userDetails.getUserPK(), request);
        return ResponseEntity.ok("식당 추가 요청이 완료되었습니다.");
    }
}