package com.type404.backend.domain.user.repository;

import com.type404.backend.domain.auth.entity.UserInfoEntity;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import com.type404.backend.domain.user.entity.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {
    List<BookmarkEntity> findAllByUserId(UserInfoEntity userId);

    Optional<BookmarkEntity> findByUserIdAndStoreId(UserInfoEntity userId, StoreInfoEntity storeId);
}
