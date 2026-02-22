package com.type404.backend.domain.review.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.review.entity.HashtagType;
import com.type404.backend.domain.review.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ReviewListResponseDTO {

    private Long reviewId;

    private String reviewWriter;

    private String reviewContents;

    private BigDecimal reviewRating;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    private List<HashtagType> hashtags;

    private List<String> reviewImages;

    public static ReviewListResponseDTO fromEntity(ReviewEntity review, List<HashtagType> hashtags, List<String> imageIds) {
        return ReviewListResponseDTO.builder()
                .reviewId(review.getReviewPK())
                .reviewWriter(review.getUserId().getUserNickname())
                .reviewContents(review.getReviewContents())
                .reviewRating(review.getReviewRating())
                .createdAt(review.getCreatedAt())
                .hashtags(hashtags)
                .reviewImages(imageIds.stream()
                        .map(id -> "/api/reviews/image/" + id)
                        .toList())
                .build();
    }
}