package com.galendar.domain.contest.repository;

import com.galendar.domain.contest.entity.ContestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ContestRepository extends JpaRepository<ContestEntity, Long> {
    boolean existsByStrNo(String strNo);
    // 현재 날짜 기준으로 아직 마감되지 않은 공모전들 조회
    @Query("SELECT c FROM ContestEntity c WHERE c.submitEndDate > :currentDate")
    List<ContestEntity> findActiveContests(@Param("currentDate") LocalDate currentDate);
}