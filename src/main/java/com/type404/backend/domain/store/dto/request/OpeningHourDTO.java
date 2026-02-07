package com.type404.backend.domain.store.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.store.entity.OpeningHoursEntity;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import com.type404.backend.domain.store.entity.enumtype.Days;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpeningHourDTO {
    @JsonProperty("day")
    private Days day;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("end_time")
    private String endTime;

    @JsonProperty("break_time")
    private String breakTime;

    public OpeningHoursEntity toEntity(StoreInfoEntity store, LocalTime bStart, LocalTime bEnd) {
        return OpeningHoursEntity.builder()
                .storeId(store)
                .days(this.day)
                .startTime(LocalTime.parse(this.startTime))
                .endTime(LocalTime.parse(this.endTime))
                .breakStartTime(bStart)
                .breakEndTime(bEnd)
                .build();
    }
}
