package com.type404.backend.global.userdetails;

import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.*;

// CustomUserDetails를 구현하기 위한 임시 user 객체
// 후에 user 엔티티가 만들어지면 삭제 예정
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TempUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userEmail;
    private String userPw;
    private String userNickname;
    private String userGender;
    private Integer userAge;
}