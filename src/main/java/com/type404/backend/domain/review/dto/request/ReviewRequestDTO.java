package com.type404.backend.domain.review.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.auth.entity.UserInfoEntity;
import com.type404.backend.domain.review.entity.HashtagType;
import com.type404.backend.domain.review.entity.ReviewEntity;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewRequestDTO {

    @NotBlank(message = "리뷰 내용은 비어있을 수 없습니다.")
    @JsonProperty("review_contents")
    private String reviewContents;

    @NotNull(message = "평점은 필수 입력 사항입니다.")
    @DecimalMin(value = "0.5", message = "평점은 최소 0.5점 이상이어야 합니다.")
    @DecimalMax(value = "5.0", message = "평점은 최대 5.0점 이하여야 합니다.")
    @JsonProperty("review_rating")
    private BigDecimal reviewRating;

    @Size(max = 3, message = "해시태그는 최대 3개까지 선택 가능합니다.")
    @Schema(description = "해시태그 목록", implementation = HashtagType.class)
    @JsonProperty("hashtag")
    private List<HashtagType> hashtags;

    @JsonProperty("review_img")
    private List<String> reviewImages;

    public ReviewEntity toEntity(UserInfoEntity user, StoreInfoEntity store) {
        return ReviewEntity.builder()
                .userId(user)
                .storeId(store)
                .reviewContents(this.reviewContents)
                .reviewRating(this.reviewRating)
                .build();
    }
}
