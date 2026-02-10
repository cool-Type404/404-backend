package com.type404.backend.domain.store.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.store.entity.StoreMenuEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponseDTO {

    @JsonProperty("menu_id")
    private Long menuPK;

    @JsonProperty("menu_name")
    private String menuName;

    @JsonProperty("price")
    private String price;

    @JsonProperty("is_rec")
    private Boolean isRec;

    @JsonProperty("menu_img")
    private String menuImg;

    public static MenuResponseDTO fromEntity(StoreMenuEntity entity) {
        return MenuResponseDTO.builder()
                .menuPK(entity.getMenuPK())
                .menuName(entity.getMenuName())
                .price(entity.getPrice())
                .isRec(entity.getIsRec())
                .menuImg(entity.getMenuImg() != null ?
                        "/api/stores/menu/image/"  + entity.getMenuPK() : null)
                .build();
    }
}