package com.type404.backend.domain.review.repository;

import com.type404.backend.domain.review.entity.ReviewEntity;
import com.type404.backend.domain.review.entity.ReviewImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImageEntity, Long> {
    List<ReviewImageEntity> findAllByReviewId(ReviewEntity reviewId);

    void deleteAllByReviewId(ReviewEntity reviewId);
}
