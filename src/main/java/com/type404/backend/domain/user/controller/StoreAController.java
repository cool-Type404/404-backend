package com.type404.backend.domain.user.controller;

import com.type404.backend.domain.user.dto.request.StoreAddRequestDTO;
import com.type404.backend.domain.user.service.StoreAddService;
import com.type404.backend.global.userdetails.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class StoreAController {
    private StoreAddService storeAddService;

    @PostMapping("/store-addition")
    public ResponseEntity<String> requestStoreAddition(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody StoreAddRequestDTO request
    ) {
        storeAddService.requestStoreAddition(userDetails.getUserPK(), request);
        return ResponseEntity.ok("식당 추가 요청이 완료되었습니다.");
    }
}
