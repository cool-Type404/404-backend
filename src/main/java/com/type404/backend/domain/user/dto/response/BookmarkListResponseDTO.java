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

    private Long storeId;

    private String storeName;

    private StoreCategory storeCategory;

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
