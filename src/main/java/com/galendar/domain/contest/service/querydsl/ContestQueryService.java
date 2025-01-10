package com.galendar.domain.contest.service.querydsl;

import com.galendar.domain.contest.dto.request.ContestRequest;
import com.galendar.domain.contest.dto.response.ContestDeadlineResponse;
import com.galendar.domain.contest.dto.response.ContestDetailResponse;
import com.galendar.domain.contest.dto.response.ContestResponse;

import java.util.List;

public interface ContestQueryService {
    List<ContestResponse> list(ContestRequest request);
    ContestDetailResponse get(Long id);
    List<ContestDeadlineResponse> findContestsBySubmitEndDate(List dates);
}
