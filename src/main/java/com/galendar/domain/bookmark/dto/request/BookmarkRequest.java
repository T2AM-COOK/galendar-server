package com.galendar.domain.bookmark.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkRequest {
    private int page;
    private int size;
    private String keyword;

    public BookmarkRequest() {
        this.page = 1;
        this.size = 10;
    }

}
