package com.type404.backend.domain.store.repository;

import com.type404.backend.domain.store.entity.StoreSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreSeatRepository extends JpaRepository<StoreSeatEntity, Long> {
    List<StoreSeatEntity> findByStoreId_StoreInfoPK(Long storeId);
}
