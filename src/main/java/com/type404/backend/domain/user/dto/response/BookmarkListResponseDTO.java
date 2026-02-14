package com.type404.backend.domain.user.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import com.type404.backend.domain.store.entity.enumtype.StoreCategory;
import com.type404.backend.domain.user.entity.BookmarkEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkListResponseDTO {
    @JsonProperty("store_info_id")
    private Long storeId;

    @JsonProperty("store_name")
    private String storeName;

    @JsonProperty("store_type")
    private StoreCategory storeCategory;

    @JsonProperty("store_rating")
    private BigDecimal storeRating;

    public static BookmarkListResponseDTO fromEntity(BookmarkEntity bookmark) {
        StoreInfoEntity store = bookmark.getStoreId();
        return BookmarkListResponseDTO.builder()
                .storeId(store.getStoreInfoPK())
                .storeName(store.getStoreName())
                .storeCategory(store.getStoreCategory())
                .storeRating(store.getAvgRating())
                .build();
    }
}
