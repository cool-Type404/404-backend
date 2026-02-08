package com.type404.backend.domain.store.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.auth.entity.enumtype.EatingLevel;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import com.type404.backend.domain.store.entity.enumtype.StoreCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreRequestDTO {
    @JsonProperty("store_name")
    private String storeName;

    @JsonProperty("store_category")
    private StoreCategory storeCategory;

    @JsonProperty("eating_level")
    private EatingLevel eatingLevel;

    @JsonProperty("store_address")
    private String storeAddress;

    @JsonProperty("store_number")
    private String storeNumber;

    @JsonProperty("store_rating")
    private BigDecimal storeRating;

    @JsonProperty("seat")
    private SeatRequestDTO seat;

    @JsonProperty("opening_hours")
    private List<OpeningHourRequestDTO> openingHours;

    @JsonProperty("menu")
    private List<MenuRequestDTO> menu;

    public StoreInfoEntity toEntity(byte[] storeImage) {
        return StoreInfoEntity.builder()
                .storeName(this.storeName)
                .storeCategory(this.storeCategory)
                .eatingLevel(this.eatingLevel)
                .storeAddress(this.storeAddress)
                .storeNumber(this.storeNumber)
                .avgRating(this.storeRating)
                .storeImage(storeImage)
                .isOpen(true)
                .build();
    }
}
