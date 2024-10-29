package com.k.garlander.domain.bookmark.dto.res;

import com.k.garlander.domain.bookmark.entity.BookmarkEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class BookmarkResponse {
    private String userId;

    private Long contestId;

    private LocalDate createdAt;

//    public static BookmarkResponse fromBookmarkEntity(BookmarkEntity bookmarkEntity) {
//        return BookmarkResponse.builder()
//                .userId(bookmarkEntity.getUser().getId())
//                .contestId(bookmarkEntity.getContest).getContest_id())
//                .createdAt(bookmarkEntity.getCreatedAt())
//                .build();
//    }
}
