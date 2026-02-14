package com.type404.backend.domain.review.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("review_writer")
    private String reviewWriter;

    @JsonProperty("review_contents")
    private String reviewContents;

    @JsonProperty("review_rating")
    private BigDecimal reviewRating;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("hashtag")
    private List<String> hashtags;

    @JsonProperty("review_img")
    private List<String> reviewImages;

    public static ReviewListResponseDTO fromEntity(ReviewEntity review, List<String> hashtags, List<String> images) {
        return ReviewListResponseDTO.builder()
                .reviewWriter(review.getUserId().getUserNickname())
                .reviewContents(review.getReviewContents())
                .reviewRating(review.getReviewRating())
                .createdAt(review.getCreatedAt())
                .hashtags(hashtags)
                .reviewImages(images.stream()
                        .map(path -> "/api/reviews/image/" + review.getReviewPK())
                        .toList())
                .build();
    }
}