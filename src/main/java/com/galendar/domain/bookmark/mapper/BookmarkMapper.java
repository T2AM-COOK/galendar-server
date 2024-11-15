package com.galendar.domain.bookmark.mapper;

import com.galendar.domain.bookmark.entity.BookmarkEntity;
import com.galendar.domain.contest.entity.ContestEntity;
import com.galendar.domain.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {

    public BookmarkEntity createEntity(UserEntity userEntity, ContestEntity contestEntity) {
        return BookmarkEntity.builder()
                .userEntity(userEntity)
                .contestEntity(contestEntity)
                .build();
    }

}
