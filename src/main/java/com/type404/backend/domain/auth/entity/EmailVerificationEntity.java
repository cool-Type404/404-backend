package com.type404.backend.domain.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_verification")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailVerificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_auth")
    private Long emailPK;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "auth_code", nullable = false)
    private String authCode;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // 만료기간
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    // 인증여부
    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified = false;

}
