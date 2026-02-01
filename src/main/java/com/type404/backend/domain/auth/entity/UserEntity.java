package com.type404.backend.domain.auth.entity;

import com.type404.backend.domain.auth.entity.enumtype.Age;
import com.type404.backend.domain.auth.entity.enumtype.EatingLevel;
import com.type404.backend.domain.auth.entity.enumtype.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userPK;

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_pw", nullable = false)
    private String userPassword;

    @Column(name = "user_img", nullable = true)
    private String userImg;
    // 나중에 기본 프로필 이미지 탑재 시, false로 변경 & default값 지정

    @Column(name = "user_nickname", nullable = false)
    private String userNickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_gender")
    private Gender userGender;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_age")
    private Age userAge;

    @Enumerated(EnumType.STRING)
    @Column(name = "eating_level", nullable = false)
    private EatingLevel eatingLevel;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

