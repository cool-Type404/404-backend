package com.type404.backend.domain.auth.entity.enumtype;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EatingLevel {
    LEVEL1("1레벨"),
    LEVEL2("2레벨"),
    LEVEL3("3레벨"),
    LEVEL4("4레벨");

    private final String description;

    @JsonValue
    public String getDescription() {
        return description;
    }
}
