package com.type404.backend.domain.review.entity;

import com.type404.backend.global.exception.CustomException;
import com.type404.backend.global.exception.ErrorCode;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum HashtagType {
    COST_EFFECTIVE("가성비 좋은"),
    TRUSTED_RESTAURANT("믿고가는 맛집"),
    FAST_TURNOVER("빠른 회전률"),
    CLEAN_STORE("쾌적한 매장"),
    KIND_SERVICE("친절한 응대"),
    HEARTY_MEAL("든든한 한끼");

    private final String description;

    public static boolean isValid(String input) {
        for (HashtagType type : HashtagType. values()) {
            if (type.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    public static void validateHashtags(List<String> hashtagInputs) {
        if (hashtagInputs == null) return;
        for (String input : hashtagInputs) {
            if (!isValid(input)) {
                throw new CustomException(ErrorCode.INVALID_PARAMETER, "잘못된 해시태그 형식입니다: " + input);
            }
        }
    }
}