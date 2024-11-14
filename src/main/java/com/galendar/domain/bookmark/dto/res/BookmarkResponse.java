package com.galendar.domain.bookmark.dto.res;

import com.galendar.domain.bookmark.entity.BookmarkEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookmarkResponse {
    private Long user;

    private Long contestId;

    private String contestName;


    public static BookmarkResponse fromBookmarkEntity(BookmarkEntity bookmarkEntity) {
        return BookmarkResponse.builder()
                .user(bookmarkEntity.getContest().getUser().getId())
                .contestId(bookmarkEntity.getContest().getId())
                .contestName(bookmarkEntity.getContest().getTitle())
                .build();
    }
}
