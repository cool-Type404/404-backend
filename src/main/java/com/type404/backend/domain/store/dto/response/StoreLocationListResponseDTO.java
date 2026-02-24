package com.type404.backend.domain.store.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreLocationListResponseDTO {

    @JsonProperty("stores")
    private List<StoreLocationResponseDTO> stores;

    public static StoreLocationListResponseDTO fromEntities(List<StoreInfoEntity> entities) {
        List<StoreLocationResponseDTO> list = entities.stream()
                .map(StoreLocationResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return StoreLocationListResponseDTO.builder()
                .stores(list)
                .build();
    }
}
