package com.type404.backend.domain.user.controller;

import com.type404.backend.domain.user.dto.response.BookmarkListResponseDTO;
import com.type404.backend.domain.user.service.BookmarkService;
import com.type404.backend.global.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/{storeId}")
    public ResponseEntity<String> addBookmark(
            @PathVariable Long storeId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        bookmarkService.addBookmark(customUserDetails.getUserInfo(), storeId);
        return ResponseEntity.ok("북마크가 추가되었습니다.");
    }

    @GetMapping
    public ResponseEntity<List<BookmarkListResponseDTO>> getMyBookmarks(
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        List<BookmarkListResponseDTO> bookmarks =
                bookmarkService.getMyBookmarks(customUserDetails.getUserInfo());

        return ResponseEntity.ok(bookmarks);
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<String> deleteBookmark(
            @PathVariable Long storeId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        bookmarkService.deleteBookmark(customUserDetails.getUserInfo(), storeId);

        return ResponseEntity.ok("북마크가 삭제되었습니다.");
    }
}
