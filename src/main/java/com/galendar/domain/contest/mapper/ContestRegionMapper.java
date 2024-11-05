package com.galendar.domain.contest.mapper;

import com.galendar.domain.contest.entity.ContestEntity;
import com.galendar.domain.contest.entity.ContestRegionEntity;
import com.galendar.domain.region.entity.RegionEntity;

public class ContestRegionMapper {

    public static ContestRegionEntity createEntity(ContestEntity contestEntity, RegionEntity regionEntity) {
        return ContestRegionEntity.builder()
                .contestEntity(contestEntity)
                .regionEntity(regionEntity)
                .build();
    }

}
