package com.galendar.domain.contest.repository;

import com.galendar.domain.contest.entity.ContestEntity;
import com.galendar.domain.contest.entity.ContestTargetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestTargetRepository extends JpaRepository<ContestTargetEntity, Long> {
    void deleteByContestEntity(ContestEntity contestEntity);

}
