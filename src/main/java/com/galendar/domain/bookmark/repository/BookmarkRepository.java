package com.galendar.domain.bookmark.repository;

import com.galendar.domain.bookmark.entity.BookmarkEntity;
import com.galendar.domain.contest.entity.ContestEntity;
import com.galendar.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {
    Optional<List<BookmarkEntity>> findByUserOrderByCreatedAtDesc(UserEntity user);
    void deleteByUserAndContest(UserEntity user, ContestEntity contest);
    Boolean existsByUserAndContest(UserEntity user, ContestEntity contest);
}
