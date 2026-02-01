package com.type404.backend.domain.user.entity;

import com.type404.backend.domain.auth.entity.UserInfoEntity;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookmark")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class BookmarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long bookmarkPK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfoEntity userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_info_id", nullable = false)
    private StoreInfoEntity storeId;
}
