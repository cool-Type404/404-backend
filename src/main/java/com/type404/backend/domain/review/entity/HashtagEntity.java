package com.type404.backend.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hashtag")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HashtagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long hashtagPK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private ReviewEntity reviewId;

    @Column(name = "hashtag_name", nullable = false)
    private String hashtagName;
}
