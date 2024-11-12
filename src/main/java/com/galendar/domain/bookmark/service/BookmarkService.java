package com.galendar.domain.bookmark.service;

import com.galendar.domain.contest.repository.ContestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final ContestRepository contestRepository;
}
