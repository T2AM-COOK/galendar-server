package com.galendar.domain.bookmark.service.querydsl;

import com.galendar.domain.bookmark.dto.request.BookmarkRequest;
import com.galendar.domain.bookmark.dto.response.BookmarkResponse;
import com.galendar.domain.bookmark.repository.querydsl.BookmarkQueryRepository;
import com.galendar.global.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookmarkQueryServiceImpl implements BookmarkQueryService {

    private final BookmarkQueryRepository bookmarkQueryRepository;
    private final UserSecurity userSecurity;

    public List<BookmarkResponse> list(BookmarkRequest request) {
        return bookmarkQueryRepository.findByUserId(request, userSecurity.getUser().getId());
    }

}
