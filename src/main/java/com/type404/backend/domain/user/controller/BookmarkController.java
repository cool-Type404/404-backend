package com.type404.backend.domain.user.controller;

import com.type404.backend.domain.user.dto.response.BookmarkListResponseDTO;
import com.type404.backend.domain.user.service.BookmarkService;
import com.type404.backend.global.userdetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "user", description = "사용자 개인화 기능 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Operation(summary = "북마크 추가", description = "특정 가게를 내 북마크 목록에 추가합니다.")
    @PostMapping("/{storeId}")
    public ResponseEntity<String> addBookmark(
            @PathVariable Long storeId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        bookmarkService.addBookmark(customUserDetails.getUserInfo(), storeId);
        return ResponseEntity.ok("북마크가 추가되었습니다.");
    }

    @Operation(summary = "내 북마크 목록 조회", description = "로그인한 사용자의 모든 북마크 리스트를 조회합니다.")
    @GetMapping
    public ResponseEntity<List<BookmarkListResponseDTO>> getMyBookmarks(
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        List<BookmarkListResponseDTO> bookmarks =
                bookmarkService.getMyBookmarks(customUserDetails.getUserInfo());

        return ResponseEntity.ok(bookmarks);
    }

    @Operation(summary = "북마크 취소", description = "등록된 북마크를 삭제합니다.")
    @DeleteMapping("/{storeId}")
    public ResponseEntity<String> deleteBookmark(
            @PathVariable Long storeId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        bookmarkService.deleteBookmark(customUserDetails.getUserInfo(), storeId);

        return ResponseEntity.ok("북마크가 삭제되었습니다.");
    }
}