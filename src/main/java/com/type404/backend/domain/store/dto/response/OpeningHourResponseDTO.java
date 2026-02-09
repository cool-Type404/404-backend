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

    @JsonProperty("opening_hours_id")
    private Long openHoursPK;

    @JsonProperty("day")
    private Days days;

    @JsonProperty("start_time")
    private LocalTime startTime;

    @JsonProperty("end_time")
    private LocalTime endTime;

    @JsonProperty("break_start_time")
    private LocalTime breakStartTime;

    @JsonProperty("break_end_time")
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