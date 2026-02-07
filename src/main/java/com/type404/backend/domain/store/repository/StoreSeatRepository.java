package com.type404.backend.domain.store.repository;

import com.type404.backend.domain.store.entity.StoreSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreSeatRepository extends JpaRepository<StoreSeatEntity, Long> {
}
