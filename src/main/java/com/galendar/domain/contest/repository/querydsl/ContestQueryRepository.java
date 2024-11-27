package com.galendar.domain.contest.repository.querydsl;

import com.galendar.domain.contest.dto.request.ContestRequest;
import com.galendar.domain.contest.dto.response.ContestDeadlineResponse;
import com.galendar.domain.contest.dto.response.ContestDetailResponse;
import com.galendar.domain.contest.dto.response.ContestResponse;
import com.galendar.domain.contest.entity.ContestEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContestQueryRepository {
    List<ContestEntity> findContestsBySubmitEndDates(List<LocalDate> deadlineDates);

    List<ContestResponse> find(ContestRequest request);
    Optional<ContestDetailResponse> findById(Long id);
    List<ContestDeadlineResponse> findContestsBySubmitEndDate(List dates);
}
