package com.galendar.domain.bookmark.controller;

import com.galendar.domain.bookmark.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
@Tag(name = "bookmark", description = "대회 북마크 관련 API")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @PostMapping
    @Operation(
            summary = "특정 대회를 북마크합니다.",
            description = "북마크하고자 하는 대회의 아이디를 파라미터로 전달합니다."
    )
    public ResponseEntity<?> addBookmark(@RequestParam(name = "contest-id") Long contestId) throws Exception {
        bookmarkService.
    }
}
