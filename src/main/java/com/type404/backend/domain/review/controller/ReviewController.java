package com.type404.backend.domain.review.controller;

import com.type404.backend.domain.review.dto.request.ReviewRequestDTO;
import com.type404.backend.domain.review.dto.response.ReviewListResponseDTO;
import com.type404.backend.domain.review.service.ReviewService;
import com.type404.backend.global.userdetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Review", description = "식당 리뷰 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "식당 리뷰 작성", description = "특정 식당에 대해 리뷰 내용, 평점, 해시태그, 이미지를 등록합니다.")
    @PostMapping("/{storeId}/reviews")
    public ResponseEntity<String> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewRequestDTO request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        reviewService.createReview(
                customUserDetails.getUserInfo(),
                storeId,
                request
        );

        return ResponseEntity.ok("리뷰가 성공적으로 등록되었습니다.");
    }

    @Operation(summary = "식당 리뷰 전체 조회", description = "특정 식당에 대한 전체 리뷰를 최신순으로 조회합니다.")
    @GetMapping("/{storeId}/reviews")
    public ResponseEntity<List<ReviewListResponseDTO>> getStoreReviews(
            @PathVariable Long storeId) {

        List<ReviewListResponseDTO> response = reviewService.getStoreReviews(storeId);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "식당 리뷰 삭제", description = "사용자가 본인이 작성한 리뷰를 삭제합니다.")
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        reviewService.deleteReview(customUserDetails.getUserInfo(), reviewId);

        return ResponseEntity.ok("리뷰가 삭제되었습니다.");
    }
}
