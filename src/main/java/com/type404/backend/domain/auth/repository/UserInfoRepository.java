package com.type404.backend.domain.auth.repository;

import com.type404.backend.domain.auth.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {
    Optional<UserInfoEntity> findByUserEmail(String userEmail);
}
