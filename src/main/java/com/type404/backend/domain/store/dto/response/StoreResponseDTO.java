package com.type404.backend.domain.store.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.auth.entity.enumtype.EatingLevel;
import com.type404.backend.domain.store.entity.*;
import com.type404.backend.domain.store.entity.enumtype.Days;
import com.type404.backend.domain.store.entity.enumtype.StoreCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponseDTO {

    private Long storeInfoPK;

    private StoreCategory storeCategory;

    private EatingLevel eatingLevel;

    private String storeName;

    private String storeAddress;

    private String storeNumber;

    private Boolean isOpen;

    private BigDecimal avgRating;

    private String storeImage;

    private List<SeatResponseDTO> seats;

    private List<OpeningHourResponseDTO> openingHours;

    private List<MenuResponseDTO> menus;

    public static StoreResponseDTO fromEntity(
            StoreInfoEntity store,
            List<StoreSeatEntity> seats,
            List<OpeningHoursEntity> openingHours,
            List<StoreMenuEntity> menus
    ) {
        return StoreResponseDTO.builder()
                .storeInfoPK(store.getStoreInfoPK())
                .storeCategory(store.getStoreCategory())
                .eatingLevel(store.getEatingLevel())
                .storeName(store.getStoreName())
                .storeAddress(store.getStoreAddress())
                .storeNumber(store.getStoreNumber())
                .isOpen(store.getIsOpen())
                .avgRating(store.getAvgRating())
                .storeImage(store.getStoreImage() != null ?
                        "/api/stores/image/" + store.getStoreInfoPK() : null)
                .seats(seats != null ?
                        seats.stream()
                                .map(SeatResponseDTO::fromEntity)
                                .collect(Collectors.toList()) : null)
                .openingHours(openingHours != null ?
                        openingHours.stream()
                                .map(OpeningHourResponseDTO::fromEntity)
                                .collect(Collectors.toList()) : null)
                .menus(menus != null ?
                        menus.stream()
                                .map(MenuResponseDTO::fromEntity)
                                .collect(Collectors.toList()) : null)
                .build();
    }
}