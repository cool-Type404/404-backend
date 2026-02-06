package com.type404.backend.domain.user.entity;

import com.type404.backend.domain.auth.entity.UserInfoEntity;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bookmark")
@Getter
@Setter
@Builder
@AllArgsConstructor
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
