package com.type404.backend.domain.review.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.auth.entity.UserInfoEntity;
import com.type404.backend.domain.review.entity.ReviewEntity;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewRequestDTO {
    @JsonProperty("reviewContents")
    private String reviewContents;

    @JsonProperty("reviewRating")
    private BigDecimal reviewRating;

    @JsonProperty("hashtag")
    private List<String> hashtags;

    @JsonProperty("reviewImg")
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
