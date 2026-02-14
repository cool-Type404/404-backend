package com.type404.backend.domain.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "refresh_token")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenPK;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    public void updateToken(String newToken) {
        this.refreshToken = newToken;
    }
}
