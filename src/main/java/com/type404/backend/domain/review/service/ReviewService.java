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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private final ReviewLikeRepository reviewLikeRepository;

    // 식당 리뷰 작성 기능
    @Transactional
    public void createReview(UserInfoEntity user, Long storeId,
                             ReviewRequestDTO request, List<MultipartFile> images) {

        StoreInfoEntity store = storeInfoRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_EXIST, "식당 정보가 존재하지 않습니다."));

        ReviewEntity review = reviewRepository.save(request.toEntity(user, store));

        saveReviewImages(review, images);
        saveHashtags(review, request.getHashtags());
    }


    // 식당 리뷰 전체 조회 기능
    public List<ReviewListResponseDTO> getStoreReviews(Long storeId) {
        StoreInfoEntity store = storeInfoRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_EXIST, "식당 정보가 존재하지 않습니다."));

        List<ReviewEntity> reviews = reviewRepository.findAllByStoreIdOrderByCreatedAtDesc(store);

        return reviews.stream().map(review -> {
            // 1. 해시태그: Enum 타입을 문자열(name)로 변환하여 List<String> 생성
            List<String> hashtags = hashtagRepository.findAllByReviewId(review).stream()
                    .map(hashtag -> hashtag.getHashtagName().name())
                    .toList();

            // 2. 이미지: byte[] 데이터가 아닌, 각 이미지 엔티티의 PK를 포함한 URL 리스트 생성
            // 여기서 생성된 List<String>이 DTO의 fromEntity 세 번째 인자로 들어갑니다.
            List<String> imageIds = reviewImageRepository.findAllByReviewId(review).stream()
                    .map(imageEntity -> String.valueOf(imageEntity.getReviewImgPK())) // 이미지 PK를 문자열로 변환
                    .toList();

            // DTO의 fromEntity 호출 (전달되는 imageIds는 이제 List<String> 타입입니다)
            return ReviewListResponseDTO.fromEntity(review, hashtags, imageIds);
        }).collect(Collectors.toList());
    }


    // 식당 리뷰 삭제 기능
    @Transactional
    public void deleteReview(UserInfoEntity user, Long reviewId) {
        ReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_EXIST, "해당 리뷰가 존재하지 않습니다."));

        if (!review.getUserId().getUserPK().equals(user.getUserPK())) {
            throw new CustomException(ErrorCode.ACCESS_DENIED, "본인이 작성한 리뷰만 삭제할 수 있습니다.");
        }

        reviewImageRepository.deleteAllByReviewId(review);
        hashtagRepository.deleteAllByReviewId(review);

        reviewRepository.delete(review);
    }


    // 식당 리뷰 좋아요 지정 기능
    @Transactional
    public void addReviewLike(UserInfoEntity user, Long reviewId) {
        ReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_EXIST, "좋아요를 누를 리뷰가 존재하지 않습니다."));

        if (reviewLikeRepository.findByUserIdAndReviewId(user, review).isEmpty()) {
            LikeEntity newLike = LikeEntity.builder()
                    .userId(user)
                    .reviewId(review)
                    .build();
            reviewLikeRepository.save(newLike);
        }
    }


    // 식당 리뷰 삭제 기능
    @Transactional
    public void removeReviewLike(UserInfoEntity user, Long reviewId) {
        ReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_EXIST, "like를 삭제할 리뷰가 존재하지 않습니다."));

        LikeEntity existingLike = reviewLikeRepository.findByUserIdAndReviewId(user, review)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_EXIST, "삭제할 like 기록이 존재하지 않습니다."));

        reviewLikeRepository.delete(existingLike);
    }



    /**
     * 식당 리뷰 저장 기능 분리
     */
    //리뷰 이미지 저장 로직
    private void saveReviewImages(ReviewEntity review, List<MultipartFile> imageFiles) {
        if (imageFiles == null || imageFiles.isEmpty()) return;

        List<ReviewImageEntity> images = imageFiles.stream().map(file -> {
            try {
                return ReviewImageEntity.builder()
                        .reviewId(review)
                        .reviewImgPath(file.getBytes())
                        .build();
            } catch (IOException e) {
                throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR, "이미지 처리 중 오류 발생");
            }
        }).collect(Collectors.toList());

        reviewImageRepository.saveAll(images);
    }

    // 해시태그 저장 로직 분리
    private void saveHashtags(ReviewEntity review, List<HashtagType> hashtagNames) {
        if (hashtagNames == null || hashtagNames.isEmpty()) return;

        List<HashtagEntity> hashtags = hashtagNames.stream().map(type -> {
                return HashtagEntity.builder()
                        .reviewId(review)
                        .hashtagName(type)
                        .build();
        }).collect(Collectors.toList());

        hashtagRepository.saveAll(hashtags);
    }
}