package com.type404.backend.domain.review.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.review.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {
    @JsonProperty("review_id")
    private Long reviewPK;

    @JsonProperty("review_contents")
    private String reviewContents;

    @JsonProperty("review_rating")
    private BigDecimal reviewRating;

    @JsonProperty("review_images")
    private List<String> reviewImages;

    public static ReviewResponseDTO fromEntity(ReviewEntity review, List<String> imagePaths) {
        return ReviewResponseDTO.builder()
                .reviewPK(review.getReviewPK())
                .reviewContents(review.getReviewContents())
                .reviewRating(review.getReviewRating())
                .reviewImages(imagePaths.stream()
                        .map(path -> "/api/reviews/image/" + review.getReviewPK())
                        .collect(Collectors.toList()))
                .build();
    }
}
