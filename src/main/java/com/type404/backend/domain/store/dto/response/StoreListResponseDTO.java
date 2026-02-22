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

    private Long storeInfoPK;

    private String storeName;

    private StoreCategory storeCategory;

    private Boolean isOpen;

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
