package com.galendar.domain.bookmark.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BookmarkResponse {

    private Long user;

    private Long contest;

    private String contestName;

    private LocalDateTime createdAt;

}
