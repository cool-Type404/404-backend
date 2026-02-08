package com.type404.backend.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store_menu")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreMenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuPK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_info_id", nullable = false)
    private StoreInfoEntity storeId;

    @Column(name = "menu_name", nullable = false)
    private String menuName;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "is_rec", nullable = false)
    private Boolean isRec = false;

    @Column(name = "menu_img", columnDefinition = "LONGBLOB")
    private byte[] menuImg;
}
