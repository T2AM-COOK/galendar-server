package com.galendar.domain.contest.service;

import com.galendar.domain.contest.dto.request.RegisterContestRequest;
import com.galendar.domain.contest.dto.response.ContestResponse;
import com.galendar.domain.user.entity.UserEntity;

import java.util.List;


public interface ContestService {
    void register(RegisterContestRequest request);
    void register(RegisterContestRequest request, UserEntity userEntity);
}