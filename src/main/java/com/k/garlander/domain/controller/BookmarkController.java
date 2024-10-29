//package com.k.garlander.domain.controller;
//
//import com.k.garlander.domain.bookmark.dto.res.BookmarkResponse;
//import com.k.garlander.domain.bookmark.entity.BookmarkEntity;
//import com.k.garlander.domain.bookmark.repository.BookmarkRepository;
//import com.k.garlander.domain.bookmark.service.BookmarkService;
//import com.k.garlander.domain.user.entity.UserEntity;
//import com.k.garlander.domain.user.repository.UserAuthHolder;
//import com.k.garlander.domain.user.repository.UserRepository;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Objects;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/bookmark")
//@Tag(name = "bookmark", description = "대회 북마크 관련 API")
//public class BookmarkController {
//
//    private final BookmarkService bookmarkService;
//    private final UserRepository userRepository;
//    private final UserAuthHolder userAuthHolder;
//    private final BookmarkRepository bookmarkRepository;
//
//    @PostMapping
//    @Operation(
//            summary = "특정 대회를 북마크합니다",
//            description = "북마크하고자 하는 대회의 아이디를 파라미터로 전달합니다"
//    )
//    public ResponseEntity<?> addBookmark(@RequestParam(name = "contest-id") Long contestId) throws Exception {
//        bookmarkService.addBookmark(Long bookmarkRequest) {
//
//            UserEntity user = userRepository.findByEmail(userAuthHolder.current().getEmail());
//
//            ContestEntity contest = contestRepository.findById(bookmarkRequest).orElse(null);
//
//            BookmarkEntity bookmark = BookmarkEntity.builder()
//                    .contest(contest)
//                    .user(user)
//                    .build();
//
//            bookmarkRepository.save(bookmark);
//        }
//
//        @Transactional
//        public void removeBookmark(Long bookmarkRequest) {
//
//            UserEntity user = userRepository.findByEmail(userAuthHolder.current().getEmail());
//
//            ContestEntity contest = contestRepository.findById(bookmarkRequest).orElseThrow(RuntimeException::new);
//
//            bookmarkRepository.deleteByUserAndContest(user, contest);
//        }
//
//        public List<BookmarkResponse> getBookmarkedContestByUser() {
//            UserEntity user = userRepository.findByEmail(userAuthHolder.current().getEmail());
//
//            return Objects.requireNonNull(bookmarkRepository.findByUser(user).orElse(null)).stream()
//                    .map(BookmarkResponse::fromBookmarkEntity)
//                    .toList();
//    }
//}