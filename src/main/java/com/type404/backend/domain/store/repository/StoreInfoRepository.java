package com.type404.backend.domain.store.repository;

import com.type404.backend.domain.store.entity.StoreInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreInfoRepository extends JpaRepository<StoreInfoEntity, Long> {
    boolean existsByStoreName(String storeName);
}
