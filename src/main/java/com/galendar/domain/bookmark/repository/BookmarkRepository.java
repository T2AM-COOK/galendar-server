package com.galendar.domain.bookmark.repository;

import com.galendar.domain.bookmark.entity.BookmarkEntity;
import com.galendar.domain.contest.entity.ContestEntity;
import com.galendar.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {
    boolean existsByUserEntityAndContestEntity(UserEntity userEntity, ContestEntity contestEntity);
    boolean existsByIdAndUserEntity(Long id, UserEntity userEntity);
    void deleteByContestEntity(ContestEntity contestEntity);

}
