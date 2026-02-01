package com.type404.backend.global.userdetails;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// CustomUserDetails를 구현하기 위한 임시 userRepository
// 후에 user repository가 만들어지면 삭제 예정
public interface TempUserRepository extends JpaRepository<TempUser, Long> {
    Optional<TempUser> findByUserEmail(String userEmail);
}
