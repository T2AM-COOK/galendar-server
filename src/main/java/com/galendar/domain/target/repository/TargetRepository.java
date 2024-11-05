package com.galendar.domain.target.repository;

import com.galendar.domain.target.entity.TargetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TargetRepository extends JpaRepository<TargetEntity, Long> {
    @Query("SELECT t FROM TargetEntity t WHERE t.del=0")
    List<TargetEntity> findActive();
}
