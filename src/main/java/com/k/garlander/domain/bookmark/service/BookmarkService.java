package com.k.garlander.domain.bookmark.service;

import com.k.garlander.domain.bookmark.entity.BookmarkEntity;
import com.k.garlander.domain.bookmark.repository.BookmarkRepository;
import com.k.garlander.domain.user.entity.UserEntity;
import com.k.garlander.domain.user.repository.UserAuthHolder;
import com.k.garlander.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkService {

//    private final ContestRepository contestRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final UserAuthHolder userAuthHolder;

    @Transactional
    public void addBookmark(Long bookmarkRequest) throws Exception {

        UserEntity user = userRepository.findByEmail(userAuthHolder.current().getEmail());

//        ContestRepository contest = contestRepository.findById(bookmarkRepository).orElse(null);

//        if (bookmarkRepository.findByUserAndContest(user, contest).isPresent()) {
//            throw new Exception("Bookmark already exists");
//        }

//        BookmarkEntity bookmark = BookmarkEntity.builder()
//                .contest(contest)
//                .user(user)
//                .build;

//        bookmarkRepository.save(bookmark);
    }

    @Transactional
    public void removeBookmark(Long bookmarkRequest) throws Exception {

        UserEntity user = userRepository.findByEmail(userAuthHolder.current().getEmail());
        // null 시 에러 반환 필요

//        ContestEntity contest = contestRepository.findById(bookmarkRequest).orElse(null);
        // null 시 에러 반환 필요

//        Optional<BookmarkEntity> bookmark = bookmarkRepository.findById(user, contest);

//        bookmarkRepository.delete(bookmark.get());
    }

    @Transactional
    public void getBookmark(Long bookmarkRequest) throws Exception {
        // 북마크 목록 조회 개발
    }
}
