package com.galendar.domain.bookmark.repository.querydsl;

import com.galendar.domain.bookmark.dto.request.BookmarkRequest;
import com.galendar.domain.bookmark.dto.response.BookmarkResponse;

import java.util.List;

public interface BookmarkQueryRepository {
    List<BookmarkResponse> findByUserId(BookmarkRequest request, Long userId);

}
