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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookmark")
@RequiredArgsConstructor
@Tag(name = "북마크", description = "북마크 관련 API")
public class BookmarkController {
    private final BookmarkService bookmarkService;
    private final BookmarkQueryService bookmarkQueryService;

    @PostMapping("/{contestId}")
    @Operation(
            summary = "북마크를 추가합니다.",
            description = "대회의 아이디를 경로로 전달합니다."
    )
    public ResponseEntity register(@PathVariable("contestId") Long contestId) {
        bookmarkService.register(contestId);
        return ResponseEntity.ok(ResponseData.ok("북마크가 등록되었습니다."));
    }

    @DeleteMapping("/{bookmarkId}")
    @Operation(
            summary = "북마크를 삭제합니다.",
            description = "대회의 아이디를 경로로 전달합니다."
    )
    public ResponseEntity remove(@PathVariable("bookmarkId") Long bookmarkId) {
        bookmarkService.remove(bookmarkId);
        return ResponseEntity.ok(ResponseData.ok("북마크가 해제되었습니다."));
    }

    @GetMapping("/list")
    @Operation(
            summary = "대회를 조회합니다.",
            description = "인자는 없습니다."
    )
    public ResponseEntity list(BookmarkRequest request) {
        List<BookmarkResponse> result = bookmarkQueryService.list(request);
        return ResponseEntity.ok(ResponseData.ok(result, "조회 성공"));
    }
}