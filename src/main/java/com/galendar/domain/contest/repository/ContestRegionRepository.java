package com.galendar.domain.contest.repository;

import com.galendar.domain.contest.entity.ContestRegionEntity;
import com.galendar.domain.contest.entity.ContestTargetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRegionRepository extends JpaRepository<ContestRegionEntity, Long> {
}
