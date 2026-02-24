package com.type404.backend.domain.store.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreLocationResponseDTO {

    @JsonProperty("store_id")
    private Long storeId;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    public static StoreLocationResponseDTO fromEntity(StoreInfoEntity entity) {
        return StoreLocationResponseDTO.builder()
                .storeId(entity.getStoreInfoPK())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .build();
    }
}
