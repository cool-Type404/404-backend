package com.type404.backend.domain.store.repository;

import com.type404.backend.domain.store.entity.StoreInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StoreInfoRepository extends JpaRepository<StoreInfoEntity, Long>, JpaSpecificationExecutor<StoreInfoEntity> {
    boolean existsByStoreName(String storeName);
}
