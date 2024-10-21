package com.galendar.domain.target.repository;

import com.galendar.domain.target.entity.TargetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetRepository extends JpaRepository <TargetEntity, Long> {
}
