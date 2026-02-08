package com.type404.backend.domain.auth.entity.enumtype;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("남성"),
    FEMALE("여성"),
    NONE("선택안함");

    private final String description;

    @JsonValue
    public String getDescription() {
        return description;
    }
}
