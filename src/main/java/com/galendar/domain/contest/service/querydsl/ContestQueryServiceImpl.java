package com.galendar.domain.contest.service.querydsl;

import com.galendar.domain.contest.dto.request.ContestRequest;
import com.galendar.domain.contest.dto.response.ContestDeadlineResponse;
import com.galendar.domain.contest.dto.response.ContestDetailResponse;
import com.galendar.domain.contest.dto.response.ContestResponse;
import com.galendar.domain.contest.exception.ContestNotFoundException;
import com.galendar.domain.contest.repository.querydsl.ContestQueryRepository;
import com.galendar.global.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContestQueryServiceImpl implements ContestQueryService {

    private final ContestQueryRepository contestQueryRepository;
    private final UserSecurity userSecurity;

    @Override
    public List<ContestResponse> list(ContestRequest request) {
        return contestQueryRepository.findWithBookmark(request, userSecurity.getUser().getId());
    }

    @Override
    public ContestDetailResponse get(Long id) {
        return contestQueryRepository.findByIdWithBookmark(id, userSecurity.getUser().getId()).orElseThrow(() -> ContestNotFoundException.EXCEPTION);
    }

    @Override
    public List<ContestDeadlineResponse> findContestsBySubmitEndDate(List dates) {
        return contestQueryRepository.findContestsBySubmitEndDates(dates);
    }


}
