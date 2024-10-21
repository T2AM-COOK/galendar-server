package com.galendar.domain.contest.repository;

import com.galendar.domain.contest.entity.ContestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRepository extends JpaRepository<ContestEntity, Long> {
}