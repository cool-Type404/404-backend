package com.type404.backend.domain.store.controller;

import com.type404.backend.domain.store.dto.request.StoreRequestDTO;
import com.type404.backend.domain.store.dto.response.StoreListResponseDTO;
import com.type404.backend.domain.store.dto.response.StoreResponseDTO;
import com.type404.backend.domain.store.service.StoreService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addStore(
            @RequestPart("storeData") StoreRequestDTO requestDTO,
            @RequestPart(value = "storeImage", required = false) MultipartFile storeImage,
            @RequestPart(value = "menuImages", required = false) List<MultipartFile> menuImages
    ) {
        storeService.createStore(requestDTO, storeImage, menuImages);

        return ResponseEntity.status(HttpStatus.CREATED).body("식당 등록 성공");
    }


    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponseDTO> getStore(
            @PathVariable("storeId") Long storeId
    ) {
        StoreResponseDTO response = storeService.getStore(storeId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<StoreListResponseDTO>> getStoreList() {
        List<StoreListResponseDTO> response = storeService.getAllStoreList();
        return ResponseEntity.ok(response);
    }
}
