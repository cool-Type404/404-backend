package com.type404.backend.domain.store.entity;

import com.type404.backend.domain.store.entity.enumtype.Days;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalTime;

@Entity
@Table(name = "opening_hours")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OpeningHoursEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opening_hours_id")
    private Long openHoursPK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_info_id", nullable = false)
    private StoreInfoEntity storeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "days")
    private Days days;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "break_start_time")
    private LocalTime breakStartTime;

    @Column(name = "break_end_time")
    private LocalTime breakEndTime;
}

