package com.type404.backend.domain.store.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.store.entity.StoreSeatEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatResponseDTO {

    private Long seatPK;

    private Boolean singleSeat;

    private Boolean doubleSeat;

    private Boolean tripleSeat;

    public static SeatResponseDTO fromEntity(StoreSeatEntity entity) {
        return SeatResponseDTO.builder()
                .seatPK(entity.getSeatPK())
                .singleSeat(entity.getSingleSeat())
                .doubleSeat(entity.getDoubleSeat())
                .tripleSeat(entity.getTripleSeat())
                .build();
    }
}