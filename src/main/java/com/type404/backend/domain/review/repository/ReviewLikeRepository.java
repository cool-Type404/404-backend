package com.type404.backend.domain.review.repository;

import com.type404.backend.domain.auth.entity.UserInfoEntity;
import com.type404.backend.domain.review.entity.LikeEntity;
import com.type404.backend.domain.review.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<LikeEntity, Long> {
    Optional<LikeEntity> findByUserIdAndReviewId(UserInfoEntity userId, ReviewEntity reviewId);

    @Modifying
    @Query("DELETE FROM LikeEntity l WHERE l.reviewId = :review")
    void deleteAllByReviewId(@Param("review") ReviewEntity review);
}
