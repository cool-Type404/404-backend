package com.type404.backend.domain.store.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import com.type404.backend.domain.store.entity.enumtype.StoreCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreListResponseDTO {
    @JsonProperty("store_info_id")
    private Long storeInfoPK;

    @JsonProperty("store_name")
    private String storeName;

    @JsonProperty("store_type")
    private StoreCategory storeCategory;

    @JsonProperty("is_open")
    private Boolean isOpen;

    @JsonProperty("store_rating")
    private BigDecimal storeRating;

    public static StoreListResponseDTO fromEntity(StoreInfoEntity entity) {
        return StoreListResponseDTO.builder()
                .storeInfoPK(entity.getStoreInfoPK())
                .storeName(entity.getStoreName())
                .storeCategory(entity.getStoreCategory())
                .isOpen(entity.getIsOpen())
                .storeRating(entity.getAvgRating())
                .build();
    }
}
