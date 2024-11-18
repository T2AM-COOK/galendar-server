package com.galendar.domain.bookmark.controller;

import com.galendar.domain.bookmark.dto.request.BookmarkRequest;
import com.galendar.domain.bookmark.dto.response.BookmarkResponse;
import com.galendar.domain.bookmark.service.BookmarkService;
import com.galendar.domain.bookmark.service.querydsl.BookmarkQueryService;
import com.galendar.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "북마크", description = "대회 북마크 관련 API")
@RestController
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final BookmarkQueryService bookmarkQueryService;

    @Operation(summary = "북마크 등록", description = "대회의 아이디를 경로로 전달합니다.")
    @PostMapping("/{contestId}")
    public ResponseEntity register(@PathVariable("contestId") Long contesetId) {
        bookmarkService.register(contesetId);
        return ResponseEntity.ok(ResponseData.ok("북마크가 등록되었습니다."));
    }

    @Operation(summary = "북마크 해제", description = "북마크를 해제합니다.")
    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity remove(@PathVariable("bookmarkId") Long bookmarkId) {
        bookmarkService.remove(bookmarkId);
        return ResponseEntity.ok(ResponseData.ok("북마크가 해제되었습니다."));
    }

    @Operation(summary = "북마크 조회", description = "등록된 북마크를 조회합니다.")
    @GetMapping("/list")
    public ResponseEntity list(BookmarkRequest request) {
        List<BookmarkResponse> result = bookmarkQueryService.list(request);
        return ResponseEntity.ok(ResponseData.ok(result, "조회 성공"));
    }
}