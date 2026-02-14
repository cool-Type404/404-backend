package com.type404.backend.domain.review.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.auth.entity.UserInfoEntity;
import com.type404.backend.domain.review.entity.HashtagType;
import com.type404.backend.domain.review.entity.ReviewEntity;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewRequestDTO {
    @JsonProperty("review_contents")
    private String reviewContents;

    @JsonProperty("review_rating")
    private BigDecimal reviewRating;

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
