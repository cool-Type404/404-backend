package com.type404.backend.domain.review.controller;

import com.type404.backend.domain.review.dto.request.ReviewRequestDTO;
import com.type404.backend.domain.review.dto.response.ReviewListResponseDTO;
import com.type404.backend.domain.review.service.ReviewService;
import com.type404.backend.global.userdetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Review", description = "리뷰 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "식당 리뷰 작성", description = "특정 식당에 대해 리뷰 내용, 평점, 해시태그, 이미지를 등록합니다.")
    @PostMapping(value = "/stores/{storeId}/reviews", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createReview(
            @PathVariable Long storeId,
            @RequestPart("request") @Valid ReviewRequestDTO request,
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        reviewService.createReview(
                customUserDetails.getUserInfo(),
                storeId,
                request,
                images
        );

        return ResponseEntity.ok("리뷰가 성공적으로 등록되었습니다.");
    }

    @Operation(summary = "식당 리뷰 전체 조회", description = "특정 식당에 대한 전체 리뷰를 최신순으로 조회합니다.")
    @GetMapping("/stores/{storeId}/reviews")
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

    @Operation(summary = "식당 리뷰 좋아요 지정", description = "다른 사용자가 작성한 리뷰에 좋아요를 지정합니다.")
    @PostMapping("/reviews/{reviewId}/like")
    public ResponseEntity<String> addLike(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        reviewService.addReviewLike(customUserDetails.getUserInfo(), reviewId);
        return ResponseEntity.ok("좋아요가 지정되었습니다.");
    }

    @Operation(summary = "식당 리뷰 좋아요 삭제", description = "다른 사용자가 작성한 리뷰에 지정한 좋아요를 삭제합니다.")
    @DeleteMapping("/reviews/{reviewId}/like")
    public ResponseEntity<String> removeLike(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        reviewService.removeReviewLike(customUserDetails.getUserInfo(), reviewId);
        return ResponseEntity.ok("좋아요가 삭제되었습니다.");
    }

    @Operation(summary = "리뷰 이미지 조회")
    @GetMapping("/reviews/image/{reviewImgId}")
    public ResponseEntity<byte[]> getReviewImage(@PathVariable Long reviewImgId) {
        byte[] imageData = reviewService.getReviewImageData(reviewImgId);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageData);
    }
}
