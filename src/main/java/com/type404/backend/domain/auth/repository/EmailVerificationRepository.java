package com.type404.backend.domain.auth.repository;

import com.type404.backend.domain.auth.entity.EmailVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationRepository
        extends JpaRepository<EmailVerificationEntity, Long> {

    Optional<EmailVerificationEntity> findByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);
}
