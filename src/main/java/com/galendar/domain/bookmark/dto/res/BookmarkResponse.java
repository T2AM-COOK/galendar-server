package com.galendar.domain.bookmark.dto.res;

import com.galendar.domain.bookmark.entity.BookmarkEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BookmarkResponse {
    private Long user;

    private Long contest;

    private String contestName;


    public static BookmarkResponse fromBookmarkEntity(BookmarkEntity bookmarkEntity) {
        return BookmarkResponse.builder()
                .user(bookmarkEntity.getContest().getUser().getId())
                .contest(bookmarkEntity.getContest().getId())
                .contestName(bookmarkEntity.getContest().getTitle())
                .build();
    }
}
