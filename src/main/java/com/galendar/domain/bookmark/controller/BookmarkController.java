package com.galendar.domain.bookmark.controller;

import com.galendar.domain.bookmark.dto.res.BookmarkResponse;
import com.galendar.domain.bookmark.exception.BookmarkException;
import com.galendar.domain.bookmark.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
@Tag(name = "bookmark", description = "대회 북마크 관련 API")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping
    @Operation(
            summary = "특정 대회 북마크 상태를 전환합니다.",
            description = "게시물의 북마크를 추가하거나 삭제합니다. 북마크하고자 하는 대회의 아이디를 파라미터로 전달합니다."
    )
    public ResponseEntity<?> toggleBookmark(@RequestParam(name = "contest-id") Long contestId) {
        try {
            bookmarkService.addOrDeleteBookmark(contestId);
            return ResponseEntity.ok().build();
        } catch (BookmarkException e) {
            log.error("북마크 동작 중 오류 발생: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("예기치 못한 오류 발생: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(
            summary = "북마크 목록을 불러옵니다.",
            description = "인자는 없습니다."
    )
    public ResponseEntity<List<BookmarkResponse>> getBookmarks() {
        return ResponseEntity.ok(bookmarkService.getBookmarkedContestsByUser());
    }
}
