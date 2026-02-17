package com.type404.backend.domain.user.service;

import com.type404.backend.domain.auth.entity.UserInfoEntity;
import com.type404.backend.domain.auth.repository.UserInfoRepository;
import com.type404.backend.domain.user.dto.request.StoreAddRequestDTO;
import com.type404.backend.domain.user.entity.StoreAdditionEntity;
import com.type404.backend.domain.user.repository.StoreAddRepository;
import com.type404.backend.global.exception.ErrorCode;
import com.type404.backend.global.exception.exceptionType.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreAddService {
    private final UserInfoRepository userInfoRepository;
    private final StoreAddRepository storeAddRepository;

    @Transactional
    public void requestStoreAddition(Long userPK, StoreAddRequestDTO request) {
        UserInfoEntity userEntity = userInfoRepository.findById(userPK)
                .orElseThrow(() -> new BadRequestException(
                        ErrorCode.DATA_NOT_EXIST,
                        "존재하지 않는 이메일입니다."
                        )
                );
        StoreAdditionEntity storeAdditionEntity = StoreAdditionEntity.builder()
                .userId(userEntity)
                .storeAddName(request.getStoreName())
                .storeAddUrl(request.getAddressUrl())
                .storeAddContents(request.getContents())
                .storeCategory(request.getStoreCategory())
                .build();
        storeAddRepository.save(storeAdditionEntity);
    }
}
