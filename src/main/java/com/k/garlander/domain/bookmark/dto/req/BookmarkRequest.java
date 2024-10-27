package com.k.garlander.domain.bookmark.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkRequest {
    private Long userId;
    private Long contestId;
}
