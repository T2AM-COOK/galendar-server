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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final ContestRepository contestRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UserAuthHolder userAuthHolder;

    @Transactional
    public void addBookmark(Long bookmarkRequest) {
        UserEntity user = userAuthHolder.current();
        ContestEntity contest = contestRepository.findById(bookmarkRequest).orElseThrow(() -> new BookmarkException("존재하지 않는 북마크"));

        bookmarkRepository.save(BookmarkEntity.builder().contest(contest).user(user).build());
    }

    @Transactional
    public void removeBookmark(Long bookmarkRequest) {
        UserEntity user = userAuthHolder.current();
        ContestEntity contest = contestRepository.findById(bookmarkRequest).orElseThrow(() -> new BookmarkException("존재하지 않는 북마크"));
        Boolean bookmarkExists = bookmarkRepository.existsByUserAndContest(user, contest);

        if (!bookmarkExists) {
            throw new BookmarkException(HttpStatus.BAD_REQUEST, "삭제할 수 없습니다.");
        }
        bookmarkRepository.deleteByUserAndContest(user, contest);
    }

    public List<BookmarkResponse> getBookmarkedContestsByUser() {
        UserEntity user = userAuthHolder.current();

        return bookmarkRepository.findByUserOrderByCreatedAtDesc(user).orElseThrow(() -> new BookmarkException("존재하지 않는 북마크)")).stream()
                .map(BookmarkResponse::fromBookmarkEntity)
                .toList();
    }
}