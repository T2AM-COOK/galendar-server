package com.k.garlander.domain.bookmark.repository;

import com.k.garlander.domain.bookmark.entity.BookmarkEntity;
import com.k.garlander.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {
    Optional<BookmarkEntity> findByUserAndContest(UserEntity user);  // 매개변수에 "PostEntity post" 추가 (혹은 Contest)
//    Optional<BookmarkEntity> findByContestId(Long contestId);
}
