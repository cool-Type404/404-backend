package com.type404.backend.domain.store.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.type404.backend.domain.store.entity.OpeningHoursEntity;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import com.type404.backend.domain.store.entity.enumtype.Days;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpeningHourRequestDTO {
    @NotNull(message = "요일을 선택해주세요.")
    @JsonProperty("day")
    private Days day;

    @NotBlank(message = "영업 시작 시간은 필수입니다.")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "시작 시간 형식이 올바르지 않습니다. (HH:mm)")
    @JsonProperty("start_time")
    private String startTime;

    @NotBlank(message = "영업 종료 시간은 필수입니다.")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "종료 시간 형식이 올바르지 않습니다. (HH:mm)")
    @JsonProperty("end_time")
    private String endTime;

    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)~([01]\\d|2[0-3]):([0-5]\\d)$|^없음$",
            message = "브레이크 타임 형식이 올바르지 않습니다. (HH:mm~HH:mm 또는 '없음')")
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
