package com.type404.backend.domain.user.service;

import com.type404.backend.domain.auth.entity.UserInfoEntity;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import com.type404.backend.domain.store.repository.StoreInfoRepository;
import com.type404.backend.domain.user.dto.response.BookmarkListResponseDTO;
import com.type404.backend.domain.user.entity.BookmarkEntity;
import com.type404.backend.domain.user.repository.BookmarkRepository;
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
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final StoreInfoRepository storeInfoRepository;

    // 북마크 지정
    @Transactional
    public void addBookmark(UserInfoEntity user, Long storeId) {
        StoreInfoEntity store = storeInfoRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_EXIST, "해당 식당 정보가 존재하지 않습니다."));

        if (bookmarkRepository.findByUserIdAndStoreId(user, store).isPresent()) {
            throw new CustomException(ErrorCode.DATA_ALREADY_EXIST, "이미 북마크에 추가된 식당입니다.");
        }

        BookmarkEntity bookmark = BookmarkEntity.builder()
                .userId(user)
                .storeId(store)
                .build();

        bookmarkRepository.save(bookmark);
    }

    // 북마크 목록 조회
    public List<BookmarkListResponseDTO> getMyBookmarks(UserInfoEntity user) {
        return bookmarkRepository.findAllByUserId(user)
                .stream()
                .map(BookmarkListResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 북마크 삭제
    @Transactional
    public void deleteBookmark(UserInfoEntity user, Long storeId) {
        StoreInfoEntity store = storeInfoRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_EXIST, "해당 식당 정보가 존재하지 않습니다."));

        BookmarkEntity bookmark = bookmarkRepository.findByUserIdAndStoreId(user, store)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_EXIST, "삭제할 북마크 내역이 존재하지 않습니다."));

        bookmarkRepository.delete(bookmark);
    }
}