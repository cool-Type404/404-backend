package com.type404.backend.domain.auth.entity;

import com.type404.backend.domain.auth.entity.enumtype.Age;
import com.type404.backend.domain.auth.entity.enumtype.EatingLevel;
import com.type404.backend.domain.auth.entity.enumtype.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_info")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoEntity {
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

    public static UserInfoEntity create(
            String email,
            String password,
            String nickname,
            Gender gender,
            Age age,
            EatingLevel eatingLevel
    ) {
        return UserInfoEntity.builder()
                .userEmail(email)
                .userPassword(password)
                .userNickname(nickname)
                .userGender(gender)
                .userAge(age)
                .eatingLevel(eatingLevel)
                .build();
    }
}

