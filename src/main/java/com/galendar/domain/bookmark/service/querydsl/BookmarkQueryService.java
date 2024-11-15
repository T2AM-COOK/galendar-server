package com.galendar.domain.bookmark.service.querydsl;

import com.galendar.domain.bookmark.dto.request.BookmarkRequest;
import com.galendar.domain.bookmark.dto.response.BookmarkResponse;

import java.util.List;

public interface BookmarkQueryService {
    List<BookmarkResponse> list(BookmarkRequest request);
}
