package com.type404.backend.domain.store.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import com.type404.backend.domain.store.entity.StoreSeatEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatDTO {
    @JsonProperty("single_seat")
    private boolean singleSeat;

    @JsonProperty("double_seat")
    private boolean doubleSeat;

    @JsonProperty("triple_seat")
    private boolean tripleSeat;

    public StoreSeatEntity toEntity(StoreInfoEntity store) {
        return StoreSeatEntity.builder()
                .storeId(store)
                .singleSeat(this.singleSeat)
                .doubleSeat(this.doubleSeat)
                .tripleSeat(this.tripleSeat)
                .build();
    }
}
