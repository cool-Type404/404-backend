package com.type404.backend.domain.review.repository;

import com.type404.backend.domain.review.entity.HashtagEntity;
import com.type404.backend.domain.review.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashtagRepository extends JpaRepository<HashtagEntity, Long> {
    List<HashtagEntity> findAllByReviewId(ReviewEntity reviewId);

    void deleteAllByReviewId(ReviewEntity reviewId);
}
