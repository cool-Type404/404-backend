package com.type404.backend.domain.store.service;

import com.type404.backend.domain.auth.entity.enumtype.EatingLevel;
import com.type404.backend.domain.store.dto.request.MenuRequestDTO;
import com.type404.backend.domain.store.dto.request.OpeningHourRequestDTO;
import com.type404.backend.domain.store.dto.request.SeatRequestDTO;
import com.type404.backend.domain.store.dto.request.StoreRequestDTO;
import com.type404.backend.domain.store.dto.response.StoreListResponseDTO;
import com.type404.backend.domain.store.dto.response.StoreResponseDTO;
import com.type404.backend.domain.store.entity.OpeningHoursEntity;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import com.type404.backend.domain.store.entity.StoreMenuEntity;
import com.type404.backend.domain.store.entity.StoreSeatEntity;
import com.type404.backend.domain.store.entity.enumtype.StoreCategory;
import com.type404.backend.domain.store.repository.*;
import com.type404.backend.global.exception.CustomException;
import com.type404.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreInfoRepository storeInfoRepository;
    private final StoreMenuRepository storeMenuRepository;
    private final StoreSeatRepository storeSeatRepository;
    private final OpeningHoursRepository openingHoursRepository;

    // 관리자 식당 등록 기능
    @Transactional
    public void createStore(StoreRequestDTO dto, MultipartFile storeImg, List<MultipartFile> menuImgs) {
        if (dto == null) {
            throw new CustomException(ErrorCode.INVALID_PARAMETER, "식당 입력 정보가 비어있습니다.");
        }
        validateDuplicateStore(dto.getStoreName());

        StoreInfoEntity savedStore = saveStoreInfo(dto, storeImg);

        saveStoreSeat(dto.getSeat(), savedStore);
        saveStoreOpeningHours(dto.getOpeningHours(), savedStore);
        saveStoreMenus(dto.getMenu(), menuImgs, savedStore);
    }


    // 식당 세부 정보 조회 기능
    public StoreResponseDTO getStore(Long storeId) {
        StoreInfoEntity store = storeInfoRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.DATA_NOT_EXIST, "해당 식당을 찾을 수 없습니다."));

        List<StoreSeatEntity> seats = storeSeatRepository.findByStoreId_StoreInfoPK(storeId);
        List<OpeningHoursEntity> openingHours = openingHoursRepository.findByStoreId_StoreInfoPK(storeId);
        List<StoreMenuEntity> menus = storeMenuRepository.findByStoreId_StoreInfoPK(storeId);

        return StoreResponseDTO.fromEntity(store, seats, openingHours, menus);
    }


    // 식당 리스트 조회 기능
    public List<StoreListResponseDTO> getAllStoreList() {
        return storeInfoRepository.findAll().stream()
                .map(StoreListResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }


    // 식당 검색 기능
    public List<StoreListResponseDTO> searchStores(String storeName, String food) {
        if ((storeName == null || storeName.isBlank()) && (food == null || food.isBlank())) {
            throw new CustomException(ErrorCode.INVALID_PARAMETER, "상호명이나 음식명 중 최소 하나는 입력해야 합니다.");
        }

        Specification<StoreInfoEntity> spec = Specification
                .where(StoreSpecifications.containsStoreName(storeName))
                .and(StoreSpecifications.containsFoodName(food));

        return storeInfoRepository.findAll(spec).stream()
                .map(StoreListResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }


    // 식당 필터링 기능
    public List<StoreListResponseDTO> filterStores(List<StoreCategory> types, List<EatingLevel> levels, List<Integer> seats) {
        Specification<StoreInfoEntity> spec = Specification
                .where(StoreSpecifications.hasCategories(types))
                .and(StoreSpecifications.hasEatingLevels(levels))
                .and(StoreSpecifications.hasSeats(seats));

        return storeInfoRepository.findAll(spec).stream()
                .map(StoreListResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }



    /**
     * [관리자 식당 등록 로직 영역]
     * 복잡한 식당 등록 프로세스를 각 도메인별(정보, 좌석, 시간, 메뉴)로 모듈화
     */
    // 삭당명 중복 체크
    private void validateDuplicateStore(String storeName) {
        if (storeName == null || storeName.isBlank()) {
            throw new CustomException(ErrorCode.INVALID_PARAMETER, "식당 이름은 필수 입력 항목입니다.");
        }
        if (storeInfoRepository.existsByStoreName(storeName)) {
            throw new CustomException(ErrorCode.DATA_ALREADY_EXIST, "이미 등록된 식당 이름입니다: " + storeName);
        }
    }

    // 식당 기본 정보 등록
    private StoreInfoEntity saveStoreInfo(StoreRequestDTO dto, MultipartFile storeImg) {
        byte[] imageBytes = null;
        if (storeImg != null && !storeImg.isEmpty()) {
            try {
                imageBytes = storeImg.getBytes();
            } catch (IOException e) {
                throw new CustomException(ErrorCode.FILE_UPLOAD_FAIL, "매장 이미지 처리 중 오류가 발생했습니다.");
            }
        }
        return storeInfoRepository.save(dto.toEntity(imageBytes));
    }

    // 좌석 정보 등록
    private void saveStoreSeat(SeatRequestDTO seatDto, StoreInfoEntity store) {
        if (seatDto == null) {
            throw new CustomException(ErrorCode.INVALID_PARAMETER, "좌석 정보가 누락되었습니다.");
        }
        storeSeatRepository.save(seatDto.toEntity(store));
    }

    // 영업 시간 등록
    private void saveStoreOpeningHours(List<OpeningHourRequestDTO> hours, StoreInfoEntity store) {
        if (hours == null || hours.isEmpty()) {
            throw new CustomException(ErrorCode.INVALID_PARAMETER, "영업 시간 정보가 최소 하나 이상 필요합니다.");
        }

        for (OpeningHourRequestDTO oh : hours) {
            LocalTime bStart = null, bEnd = null;
            if (oh.getBreakTime() != null && !oh.getBreakTime().equals("없음")) {
                if (!oh.getBreakTime().contains("~")) {
                    throw new CustomException(ErrorCode.INVALID_PARAMETER,
                            oh.getDay() + " 요일의 브레이크 타임 형식이 잘못되었습니다. (예: 15:00~17:00)");
                }
                try {
                    String[] times = oh.getBreakTime().split("~");
                    bStart = LocalTime.parse(times[0].trim());
                    bEnd = LocalTime.parse(times[1].trim());
                } catch (Exception e) {
                    throw new CustomException(ErrorCode.INVALID_PARAMETER,
                            oh.getDay() + " 요일의 시간 파싱에 실패했습니다.");
                }
            }
            openingHoursRepository.save(oh.toEntity(store, bStart, bEnd));
        }
    }

    // 메뉴 등록
    private void saveStoreMenus(List<MenuRequestDTO> menus, List<MultipartFile> menuImgs, StoreInfoEntity store) {
        if (menus == null || menus.isEmpty()) {
            throw new CustomException(ErrorCode.INVALID_PARAMETER, "메뉴 정보가 최소 하나 이상 필요합니다.");
        }

        int imgCount = (menuImgs == null) ? 0 : menuImgs.size();

        if (menus.size() != imgCount) {
            throw new CustomException(ErrorCode.INVALID_FORMAT,
                    String.format("메뉴 개수(%d)와 이미지 개수(%d)가 일치하지 않습니다.", menus.size(), imgCount));
        }

        for (int i = 0; i < menus.size(); i++) {
            byte[] pic = null;
            MultipartFile mImg = menuImgs.get(i);

            if (mImg != null && !mImg.isEmpty()) {
                try {
                    pic = mImg.getBytes();
                } catch (IOException e) {
                    throw new CustomException(ErrorCode.FILE_UPLOAD_FAIL,
                            (i + 1) + "번째 메뉴 이미지 처리 중 오류가 발생했습니다.");
                }
            }

            storeMenuRepository.save(menus.get(i).toEntity(store, pic));
        }
    }

}