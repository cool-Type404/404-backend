package com.type404.backend.domain.review.controller;

import com.type404.backend.domain.review.dto.request.ReviewRequestDTO;
import com.type404.backend.domain.review.dto.response.ReviewResponseDTO;
import com.type404.backend.domain.review.service.ReviewService;
import com.type404.backend.global.userdetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Review", description = "식당 리뷰 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "식당 리뷰 작성", description = "특정 식당에 대해 리뷰 내용, 평점, 해시태그, 이미지를 등록합니다.")
    @PostMapping("/{storeId}/reviews")
    public ResponseEntity<ReviewResponseDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewRequestDTO request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        ReviewResponseDTO response = reviewService.createReview(
                customUserDetails.getUserInfo(),
                storeId,
                request
        );

        return ResponseEntity.ok(response);
    }
}
