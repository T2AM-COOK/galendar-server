package com.k.garlander.domain.bookmark.repository;

import com.k.garlander.domain.bookmark.entity.BookmarkEntity;
import com.k.garlander.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {
    Optional<List<BookmarkEntity>> findByUser(UserEntity user);
//    void deleteByUserAndContest(UserEntity user, ContestEntity contest);
}
