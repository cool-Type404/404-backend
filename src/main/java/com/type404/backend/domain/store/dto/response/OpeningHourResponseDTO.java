package com.type404.backend.domain.store.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.store.entity.OpeningHoursEntity;
import com.type404.backend.domain.store.entity.enumtype.Days;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpeningHourResponseDTO {

    private Long openHoursPK;

    private Days days;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalTime breakStartTime;

    private LocalTime breakEndTime;

    public static OpeningHourResponseDTO fromEntity(OpeningHoursEntity entity) {
        return OpeningHourResponseDTO.builder()
                .openHoursPK(entity.getOpenHoursPK())
                .days(entity.getDays())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .breakStartTime(entity.getBreakStartTime())
                .breakEndTime(entity.getBreakEndTime())
                .build();
    }
}