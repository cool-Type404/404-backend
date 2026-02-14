package com.type404.backend.domain.review.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonProperty("review_id")
    private Long reviewId;

    @JsonProperty("review_writer")
    private String reviewWriter;

    @JsonProperty("review_contents")
    private String reviewContents;

    @JsonProperty("review_rating")
    private BigDecimal reviewRating;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @JsonProperty("hashtag")
    private List<String> hashtags;

    @JsonProperty("review_img")
    private List<String> reviewImages;

    public static ReviewListResponseDTO fromEntity(ReviewEntity review, List<String> hashtags, List<String> imageIds) {
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