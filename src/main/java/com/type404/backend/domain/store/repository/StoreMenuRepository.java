package com.type404.backend.domain.store.repository;

import com.type404.backend.domain.store.entity.StoreMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreMenuRepository extends JpaRepository<StoreMenuEntity, Long> {
}
