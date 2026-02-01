package com.type404.backend.domain.store.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store_seat")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class StoreSeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long seatPK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_info_id", nullable = false)
    private StoreInfoEntity storeId;

    @Column(name = "single_seat", nullable = false)
    private Boolean singleSeat = false;

    @Column(name = "double_seat", nullable = false)
    private Boolean doubleSeat = false;

    @Column(name = "triple_seat", nullable = false)
    private  Boolean tripleSeat = false;
}
