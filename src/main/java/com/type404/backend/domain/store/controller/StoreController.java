package com.type404.backend.domain.store.controller;

import com.type404.backend.domain.auth.entity.enumtype.EatingLevel;
import com.type404.backend.domain.store.dto.request.StoreRequestDTO;
import com.type404.backend.domain.store.dto.response.StoreListResponseDTO;
import com.type404.backend.domain.store.dto.response.StoreLocationListResponseDTO;
import com.type404.backend.domain.store.dto.response.StoreResponseDTO;
import com.type404.backend.domain.store.entity.enumtype.StoreCategory;
import com.type404.backend.domain.store.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Store", description = "식당 관련 API")
@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @Operation(summary = "관리자 식당 등록", description = "식당 정보, 좌석, 영업시간, 메뉴 및 이미지를 등록합니다.")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addStore(
            @RequestPart("storeData") @Valid StoreRequestDTO requestDTO,
            @RequestPart(value = "storeImage", required = false) MultipartFile storeImage,
            @RequestPart(value = "menuImages", required = false) List<MultipartFile> menuImages
    ) {
        storeService.createStore(requestDTO, storeImage, menuImages);
        return ResponseEntity.status(HttpStatus.CREATED).body("식당 등록 성공");
    }

    @Operation(summary = "식당 상세 정보 조회", description = "특정 식당의 상세 정보를 조회합니다.")
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponseDTO> getStore(
            @PathVariable("storeId") Long storeId
    ) {
        StoreResponseDTO response = storeService.getStore(storeId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "전체 식당 리스트 조회", description = "등록된 모든 식당의 요약 리스트를 반환합니다.")
    @GetMapping
    public ResponseEntity<List<StoreListResponseDTO>> getStoreList() {
        List<StoreListResponseDTO> response = storeService.getAllStoreList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "전체 매장 위경도 반환", description = "모든 매장의 주소를 지오코딩하여 위·경도 목록을 반환합니다.")
    @PostMapping("/locations")
    public ResponseEntity<StoreLocationListResponseDTO> syncStoreLocations() {
        StoreLocationListResponseDTO response = storeService.syncAllStoreCoordinates();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "식당 상호명/메뉴 검색", description = "상호명 또는 메뉴 이름을 통해 식당을 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<List<StoreListResponseDTO>> searchStores(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String food
    ) {
        List<StoreListResponseDTO> response = storeService.searchStores(storeName, food);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "식당 조건 필터링", description = "카테고리, 혼밥 레벨, 좌석 조건을 조합하여 식당을 필터링합니다.")
    @GetMapping("/filter")
    public ResponseEntity<List<StoreListResponseDTO>> filterStores(
            @RequestParam(required = false) List<StoreCategory> storeType,
            @RequestParam(required = false) List<EatingLevel> eatingLevel,
            @RequestParam(required = false) List<Integer> seat
    ) {
        List<StoreListResponseDTO> response = storeService.filterStores(storeType, eatingLevel, seat);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "매장 이미지 조회")
    @GetMapping("/image/{storeId}")
    public ResponseEntity<Resource> getStoreImage(@PathVariable Long storeId) {
        byte[] image = storeService.getStoreImageBytes(storeId);
        if (image == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new ByteArrayResource(image));
    }

    @Operation(summary = "메뉴 이미지 조회")
    @GetMapping("/menu/image/{menuId}")
    public ResponseEntity<Resource> getMenuImage(@PathVariable Long menuId) {
        byte[] image = storeService.getMenuImageBytes(menuId);
        if (image == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new ByteArrayResource(image));
    }

    // 문자열을 enum으로 자동 변환
    @InitBinder
    public void initBinder(org.springframework.web.bind.WebDataBinder binder) {
        binder.registerCustomEditor(EatingLevel.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(EatingLevel.from(text));
            }
        });
    }
}
