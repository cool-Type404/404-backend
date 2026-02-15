package com.type404.backend.domain.user.repository;

import com.type404.backend.domain.user.entity.StoreAdditionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreAddRepository extends JpaRepository<StoreAdditionEntity, Long> {
}
