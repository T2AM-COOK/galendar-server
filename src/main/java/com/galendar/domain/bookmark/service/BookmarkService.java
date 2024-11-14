package com.galendar.domain.bookmark.service;

import com.galendar.domain.bookmark.dto.res.BookmarkResponse;
import com.galendar.domain.bookmark.entity.BookmarkEntity;
import com.galendar.domain.bookmark.exception.BookmarkException;
import com.galendar.domain.bookmark.repository.BookmarkRepository;
import com.galendar.domain.contest.entity.ContestEntity;
import com.galendar.domain.contest.repository.ContestRepository;
import com.galendar.domain.user.entity.UserEntity;
import com.galendar.domain.user.repository.UserAuthHolder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final ContestRepository contestRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UserAuthHolder userAuthHolder;

    @Transactional
    public void addOrDeleteBookmark(Long bookmarkRequest) {
        UserEntity user = userAuthHolder.current();
        ContestEntity contest = contestRepository.findById(bookmarkRequest).orElseThrow(() -> new BookmarkException("존재하지 않는 대회"));
        Boolean bookmarkExists = bookmarkRepository.existsByUserAndContest(user, contest);

        if (!bookmarkExists) {
            bookmarkRepository.save(BookmarkEntity.builder()
                    .contest(contest)
                    .user(user)
                    .build());
        } else {
            bookmarkRepository.deleteByUserAndContest(user, contest);
        }

        bookmarkRepository.save(BookmarkEntity.builder().contest(contest).user(userAuthHolder.current()).build());
    }

    public List<BookmarkResponse> getBookmarkedContestsByUser() {
        return bookmarkRepository.findByUserOrderByCreatedAtDesc(userAuthHolder.current()).orElseThrow(() -> new BookmarkException("존재하지 않는 북마크)")).stream()
                .map(BookmarkResponse::fromBookmarkEntity)
                .toList();
    }
}