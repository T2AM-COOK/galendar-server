package com.galendar.domain.contest.mapper;

import com.galendar.domain.contest.entity.ContestEntity;
import com.galendar.domain.contest.entity.ContestTargetEntity;
import com.galendar.domain.target.entity.TargetEntity;


public class ContestTargetMapper {

    public static ContestTargetEntity createEntity(ContestEntity contestEntity, TargetEntity targetEntity) {
        return ContestTargetEntity.builder()
                .contestEntity(contestEntity)
                .targetEntity(targetEntity)
                .build();
    }

}
