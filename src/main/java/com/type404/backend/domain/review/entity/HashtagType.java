package com.type404.backend.domain.review.entity;

public enum HashtagType {
    혼밥, 맛집, 친절함, 분위기좋은, 가성비, 조용한, 역세권;

    // 문자열이 유효한지 체크하는 유틸리티 메서드
    public static boolean isValid(String name) {
        for (HashtagType type : HashtagType.values()) {
            if (type.name().equals(name)) return true;
        }
        return false;
    }
}
