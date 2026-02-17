package com.type404.backend.domain.review.repository;

import com.type404.backend.domain.auth.entity.UserInfoEntity;
import com.type404.backend.domain.review.entity.LikeEntity;
import com.type404.backend.domain.review.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<LikeEntity, Long> {
    Optional<LikeEntity> findByUserIdAndReviewId(UserInfoEntity userId, ReviewEntity reviewId);
}
