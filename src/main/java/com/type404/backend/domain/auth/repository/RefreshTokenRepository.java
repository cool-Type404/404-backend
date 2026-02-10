package com.type404.backend.domain.auth.repository;

import com.type404.backend.domain.auth.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByUserEmail(String userEmail);
    void deleteByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);
}
