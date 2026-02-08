package com.type404.backend.domain.store.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.auth.entity.enumtype.EatingLevel;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import com.type404.backend.domain.store.entity.enumtype.StoreCategory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank(message = "식당 이름은 필수입니다.")
    @JsonProperty("store_name")
    private String storeName;

    @NotNull(message = "카테고리를 선택해주세요.")
    @JsonProperty("store_category")
    private StoreCategory storeCategory;

    @NotNull(message = "혼밥 레벨은 필수입니다.")
    @JsonProperty("eating_level")
    private EatingLevel eatingLevel;

    @NotBlank(message = "식당 주소는 필수입니다.")
    @JsonProperty("store_address")
    private String storeAddress;

    @Pattern(
            regexp = "^(02|0[3-9]{1}[0-9]{1}|010)-[0-9]{3,4}-[0-9]{4}$",
            message = "전화번호 형식이 올바르지 않습니다. (예: 02-123-4567 또는 010-1234-5678)"
    )
    @JsonProperty("store_number")
    private String storeNumber;

    @JsonProperty("store_rating")
    private BigDecimal storeRating;

    @Valid
    @NotNull(message = "좌석 정보가 누락되었습니다.")
    @JsonProperty("seat")
    private SeatRequestDTO seat;

    @Valid
    @NotEmpty(message = "영업 시간 정보가 최소 하나 이상 필요합니다.")
    @JsonProperty("opening_hours")
    private List<OpeningHourRequestDTO> openingHours;

    @Valid
    @NotEmpty(message = "메뉴 정보가 최소 하나 이상 필요합니다.")
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
