package com.type404.backend.domain.store.entity;

import com.type404.backend.domain.auth.entity.enumtype.EatingLevel;
import com.type404.backend.domain.store.entity.enumtype.StoreCategory;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "store_info")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_info_id", nullable = false)
    private Long storeInfoPK;

    @Enumerated(EnumType.STRING)
    @Column(name = "store_type")
    private StoreCategory storeCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "eating_level", nullable = false)
    private EatingLevel eatingLevel;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "store_address", nullable = false)
    private String storeAddress;

    @Column(name = "store_number")
    private String storeNumber;

    @Column(name = "current_open")
    private Boolean isOpen;

    @Column(name = "avg_rating", precision = 2, scale = 1)
    private BigDecimal avgRating;

    @Column(name = "store_img")
    private String storeImg;
}
