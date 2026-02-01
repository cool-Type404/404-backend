package com.type404.backend.domain.user.entity;

import com.type404.backend.domain.auth.entity.UserEntity;
import com.type404.backend.domain.user.entity.enumtype.StoreCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store_addition")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class StoreAdditionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeadd_id")
    private Long storeAddPK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "storeadd_name", nullable = false)
    private String storeAddName;

    @Column(name = "storeadd_address")
    private String storeAddUrl;

    @Column(name = "storeadd_contents")
    private String storeAddContents;

    @Enumerated(EnumType.STRING)
    @Column(name = "storeadd_category")
    private StoreCategory storeCategory;

}

