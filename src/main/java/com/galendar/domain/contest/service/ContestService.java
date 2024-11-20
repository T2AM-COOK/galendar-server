package com.galendar.domain.contest.service;

import com.galendar.domain.contest.dto.request.RegisterContestRequest;
import com.galendar.domain.user.entity.UserEntity;


public interface ContestService {
    void register(RegisterContestRequest request);
    void register(RegisterContestRequest request, UserEntity userEntity);
    void remove(Long id);
}