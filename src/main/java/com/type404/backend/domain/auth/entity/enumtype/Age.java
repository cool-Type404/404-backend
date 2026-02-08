package com.type404.backend.domain.auth.entity.enumtype;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Age {
    UNDER_TEENS("10대 이하"),
    TEENS("10대"),
    TWENTIES("20대"),
    THIRTIES("30대"),
    FORTIES("40대"),
    OVER_FIFTIES("50대 이상"),
    NONE("선택안함");

    private final String description;

    @JsonValue
    public String getDescription() {
        return description;
    }
}
