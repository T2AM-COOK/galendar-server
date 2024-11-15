package com.galendar.domain.bookmark.service;

import com.galendar.domain.bookmark.exception.BookmarkAlreadyExistsException;
import com.galendar.domain.bookmark.exception.BookmarkNotFoundException;
import com.galendar.domain.bookmark.mapper.BookmarkMapper;
import com.galendar.domain.bookmark.repository.BookmarkRepository;
import com.galendar.domain.contest.entity.ContestEntity;
import com.galendar.domain.contest.mapper.ContestMapper;
import com.galendar.domain.user.entity.UserEntity;
import com.galendar.global.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper bookmarkMapper;
    private final ContestMapper contestMapper;
    private final UserSecurity userSecurity;

    public void register(Long contestId) {
        UserEntity userEntity = userSecurity.getUserEntity();
        ContestEntity contestEntity = contestMapper.createEntity(contestId);
        if (!bookmarkRepository.existsByUserEntityAndContestEntity(userEntity, contestEntity)) {
            bookmarkRepository.save(bookmarkMapper.createEntity(userEntity, contestEntity));
        } else {
            throw BookmarkAlreadyExistsException.EXCEPTION;
        }
    }

    public void remove(Long bookmarkId) {
        if (bookmarkRepository.existsByIdAndUserEntity(bookmarkId, userSecurity.getUserEntity())) {
            bookmarkRepository.deleteById(bookmarkId);
        } else {
            throw BookmarkNotFoundException.EXCEPTION;
        }
    }

}
