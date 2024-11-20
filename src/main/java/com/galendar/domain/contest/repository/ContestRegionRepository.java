package com.galendar.domain.contest.repository;

import com.galendar.domain.contest.entity.ContestEntity;
import com.galendar.domain.contest.entity.ContestRegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRegionRepository extends JpaRepository<ContestRegionEntity, Long> {
    void deleteByContestEntity(ContestEntity contestEntity);
}
