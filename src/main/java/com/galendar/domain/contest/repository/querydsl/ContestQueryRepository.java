package com.galendar.domain.contest.repository.querydsl;

import com.galendar.domain.contest.dto.request.ContestRequest;
import com.galendar.domain.contest.dto.response.ContestDeadlineResponse;
import com.galendar.domain.contest.dto.response.ContestDetailResponse;
import com.galendar.domain.contest.dto.response.ContestResponse;

import java.util.List;
import java.util.Optional;

public interface ContestQueryRepository {

    List<ContestResponse> find(ContestRequest request);
    Optional<ContestDetailResponse> findById(Long id);
    List<ContestDeadlineResponse> findContestsBySubmitEndDates(List deadlineDates);
}
