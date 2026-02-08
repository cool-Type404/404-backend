package com.type404.backend.domain.store.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import com.type404.backend.domain.store.entity.StoreMenuEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuRequestDTO {
    @JsonProperty("menu_name")
    private String menuName;

    @JsonProperty("price")
    private String price;

    @JsonProperty("is_rec")
    private boolean isRec;

    public StoreMenuEntity toEntity(StoreInfoEntity store, byte[] menuImg) {
        return StoreMenuEntity.builder()
                .storeId(store)
                .menuName(this.menuName)
                .price(this.price)
                .isRec(this.isRec)
                .menuImg(menuImg)
                .build();
    }
}
