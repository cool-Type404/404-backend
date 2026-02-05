package com.type404.backend.domain.auth.repository;

import com.type404.backend.domain.auth.entity.EmailVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository
        extends JpaRepository<EmailVerificationEntity, Long> {

    Optional<EmailVerificationEntity> findByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);
}
