package com.type404.backend.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store_image")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_img_id")
    private Long storeImgPK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_info_id", nullable = false)
    private StoreInfoEntity storeId;

    @Column(name = "store_img_path", nullable = false)
    private String storeImgPath;
}
