package com.type404.backend.domain.user.entity;

import com.type404.backend.domain.auth.entity.UserInfoEntity;
import com.type404.backend.domain.review.entity.ReviewEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review_like")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likePK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfoEntity userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private ReviewEntity reviewId;
}
