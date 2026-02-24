package com.type404.backend.domain.store.repository;

import com.type404.backend.domain.store.entity.StoreInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StoreInfoRepository extends JpaRepository<StoreInfoEntity, Long>, JpaSpecificationExecutor<StoreInfoEntity> {
    boolean existsByStoreName(String storeName);

    /** 위·경도가 모두 있는 식당만 조회 (지도 마커용) */
    List<StoreInfoEntity> findByLatitudeIsNotNullAndLongitudeIsNotNull();
}
