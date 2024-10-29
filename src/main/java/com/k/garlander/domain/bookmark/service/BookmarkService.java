package com.k.garlander.domain.bookmark.service;

import com.k.garlander.domain.bookmark.dto.res.BookmarkResponse;
import com.k.garlander.domain.bookmark.entity.BookmarkEntity;
import com.k.garlander.domain.bookmark.repository.BookmarkRepository;
import com.k.garlander.domain.user.entity.UserEntity;
import com.k.garlander.domain.user.repository.UserAuthHolder;
import com.k.garlander.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkService {

//    private final ContestRepository contestRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final UserAuthHolder userAuthHolder;

//    @Transactional
//    public void addBookmark(Long bookmarkRequest) {
//
//        UserEntity user = userRepository.findByEmail(userAuthHolder.current().getEmail());
//
//        ContestEntity contest = contestRepository.findById(bookmarkRequest).orElse(null);
//
//        BookmarkEntity bookmark = BookmarkEntity.builder()
//                .contest(contest)
//                .user(user)
//                .build();
//
//        bookmarkRepository.save(bookmark);
//    }

    @Transactional
    public void removeBookmark(Long bookmarkRequest) {

        UserEntity user = userRepository.findByEmail(userAuthHolder.current().getEmail());

//        ContestEntity contest = contestRepository.findById(bookmarkRequest).orElseThrow(RuntimeException::new);

//        bookmarkRepository.deleteByUserAndContest(user, contest);
    }

    public List<BookmarkResponse> getBookmarkedContestByUser() {
        UserEntity user = userRepository.findByEmail(userAuthHolder.current().getEmail());

//        return Objects.requireNonNull(bookmarkRepository.findByUser(user).orElse(null)).stream()
//                .map(BookmarkResponse::fromBookmarkEntity)
//                .toList();
    }
}
