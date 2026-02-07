package com.type404.backend.domain.store.controller;

import com.type404.backend.domain.store.dto.request.StoreRequestDTO;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addStore(
            @RequestPart("storeData") StoreRequestDTO requestDTO,
            @RequestPart(value = "storeImage", required = false) MultipartFile storeImage,
            @RequestPart(value = "menuImages", required = false) List<MultipartFile> menuImages
    ) {
        storeService.createStore(requestDTO, storeImage, menuImages);

        return ResponseEntity.status(HttpStatus.CREATED).body("식당 등록 성공");
    }
}
