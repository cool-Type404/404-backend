package com.type404.backend.domain.review.repository;

import com.type404.backend.domain.review.entity.ReviewEntity;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findAllByStoreIdOrderByCreatedAtDesc(StoreInfoEntity storeId);
}
