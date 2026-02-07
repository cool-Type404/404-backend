package com.type404.backend.domain.store.repository;

import com.type404.backend.domain.store.entity.OpeningHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpeningHoursRepository extends JpaRepository<OpeningHoursEntity, Long> {
}
