package com.type404.backend.domain.review.service;

import com.type404.backend.domain.auth.entity.UserInfoEntity;
import com.type404.backend.domain.review.dto.request.ReviewRequestDTO;
import com.type404.backend.domain.review.dto.response.ReviewListResponseDTO;
import com.type404.backend.domain.review.entity.*;
import com.type404.backend.domain.review.repository.*;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import com.type404.backend.domain.store.repository.StoreInfoRepository;
import com.type404.backend.global.exception.CustomException;
import com.type404.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final HashtagRepository hashtagRepository;
    private final StoreInfoRepository storeInfoRepository;

    @Transactional
    public void createReview(UserInfoEntity user, Long storeId, ReviewRequestDTO request) {
        StoreInfoEntity store = storeInfoRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_EXIST, "식당 정보가 존재하지 않습니다."));

        ReviewEntity review = reviewRepository.save(request.toEntity(user, store));

        saveReviewImages(review, request.getReviewImages());
        saveHashtags(review, request.getHashtags());
    }

    public List<ReviewListResponseDTO> getStoreReviews(Long storeId) {
        StoreInfoEntity store = storeInfoRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_EXIST, "식당 정보가 존재하지 않습니다."));

        List<ReviewEntity> reviews = reviewRepository.findAllByStoreIdOrderByCreatedAtDesc(store);

        return reviews.stream().map(review -> {
            List<String> hashtags = hashtagRepository.findAllByReviewId(review).stream()
                    .map(HashtagEntity::getHashtagName)
                    .toList();

            List<String> images = reviewImageRepository.findAllByReviewId(review).stream()
                    .map(ReviewImageEntity::getReviewImgPath)
                    .toList();

            return ReviewListResponseDTO.fromEntity(review, hashtags, images);
        }).collect(Collectors.toList());
    }




    /**
     * 리뷰 이미지 저장 로직 분리
     */
    private void saveReviewImages(ReviewEntity review, List<String> imagePaths) {
        if (imagePaths == null || imagePaths.isEmpty()) return;

        List<ReviewImageEntity> images = imagePaths.stream()
                .map(path -> ReviewImageEntity.builder()
                        .reviewId(review)
                        .reviewImgPath(path)
                        .build())
                .collect(Collectors.toList());

        reviewImageRepository.saveAll(images);
    }

    /**
     * 해시태그 저장 로직 분리
     */
    private void saveHashtags(ReviewEntity review, List<String> hashtagNames) {
        if (hashtagNames == null || hashtagNames.isEmpty()) return;

        List<HashtagEntity> hashtags = hashtagNames.stream()
                .map(name -> HashtagEntity.builder()
                        .reviewId(review)
                        .hashtagName(name)
                        .build())
                .collect(Collectors.toList());

        hashtagRepository.saveAll(hashtags);
    }
}