package com.galendar.domain.region.repository;

import com.galendar.domain.region.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegionRepository extends JpaRepository<RegionEntity, Long> {
    @Query("SELECT r FROM RegionEntity r WHERE r.del=0")
    List<RegionEntity> findActive();

}
