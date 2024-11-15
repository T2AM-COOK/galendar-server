package com.galendar.domain.contest.mapper;

import com.galendar.domain.contest.dto.request.RegisterContestRequest;
import com.galendar.domain.contest.entity.ContestEntity;
import com.galendar.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContestMapper {
    public ContestEntity createEntity(Long contestId) {
        return ContestEntity.builder().id(contestId).build();
    }

    public ContestEntity createEntity(RegisterContestRequest request, UserEntity userEntity) {
        return ContestEntity.builder()
                .userEntity(
                        userEntity
                )
                .title(request.getTitle())
                .content(request.getContent())
                .cost(request.getCost())
                .link(request.getLink())
                .imgLink(request.getImgLink())
                .submitStartDate(request.getSubmitStartDate())
                .submitEndDate(request.getSubmitEndDate())
                .contestStartDate(request.getContestStartDate())
                .contestEndDate(request.getContestEndDate())
                .strNo(request.getStrNo())
                .build();
    }

}
