package com.galendar.domain.contest.service;

import com.galendar.domain.contest.dto.request.RegisterContestRequest;
import com.galendar.domain.contest.dto.response.ContestResponse;

import java.util.List;


public interface ContestService {
    void register(RegisterContestRequest request);

}