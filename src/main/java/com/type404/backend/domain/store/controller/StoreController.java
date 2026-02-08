package com.type404.backend.domain.store.controller;

import com.type404.backend.domain.auth.entity.enumtype.EatingLevel;
import com.type404.backend.domain.store.dto.request.StoreRequestDTO;
import com.type404.backend.domain.store.dto.response.StoreListResponseDTO;
import com.type404.backend.domain.store.dto.response.StoreResponseDTO;
import com.type404.backend.domain.store.entity.enumtype.StoreCategory;
import com.type404.backend.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    // 관리자 식당 등록 기능
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addStore(
            @RequestPart("storeData") StoreRequestDTO requestDTO,
            @RequestPart(value = "storeImage", required = false) MultipartFile storeImage,
            @RequestPart(value = "menuImages", required = false) List<MultipartFile> menuImages
    ) {
        storeService.createStore(requestDTO, storeImage, menuImages);
        return ResponseEntity.status(HttpStatus.CREATED).body("식당 등록 성공");
    }

    // 식당 세부 정보 조회 기능
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponseDTO> getStore(
            @PathVariable("storeId") Long storeId
    ) {
        StoreResponseDTO response = storeService.getStore(storeId);
        return ResponseEntity.ok(response);
    }

    // 식당 리스트 조회 기능
    @GetMapping
    public ResponseEntity<List<StoreListResponseDTO>> getStoreList() {
        List<StoreListResponseDTO> response = storeService.getAllStoreList();
        return ResponseEntity.ok(response);
    }

    // 식당 검색 기능
    @GetMapping("/search")
    public ResponseEntity<List<StoreListResponseDTO>> searchStores(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String food
    ) {
        List<StoreListResponseDTO> response = storeService.searchStores(storeName, food);
        return ResponseEntity.ok(response);
    }

    // 식당 필터링 기능
    @GetMapping("/filter")
    public ResponseEntity<List<StoreListResponseDTO>> filterStores(
            @RequestParam(required = false) List<StoreCategory> storeType,
            @RequestParam(required = false) List<EatingLevel> eatingLevel,
            @RequestParam(required = false) List<Integer> seat
    ) {
        List<StoreListResponseDTO> response = storeService.filterStores(storeType, eatingLevel, seat);
        return ResponseEntity.ok(response);
    }
}
