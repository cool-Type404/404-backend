package com.type404.backend.domain.store.repository;

import com.type404.backend.domain.store.entity.OpeningHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpeningHoursRepository extends JpaRepository<OpeningHoursEntity, Long> {
    List<OpeningHoursEntity> findByStoreId_StoreInfoPK(Long storeId);
}
