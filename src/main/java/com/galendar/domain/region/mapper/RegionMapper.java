package com.galendar.domain.region.mapper;

import com.galendar.domain.region.dto.RegionDTO;
import com.galendar.domain.region.dto.request.RegisterRegionRequest;
import com.galendar.domain.region.entity.RegionEntity;
import com.galendar.domain.target.dto.request.RegisterTargetRequest;
import com.galendar.domain.target.entity.TargetEntity;
import org.springframework.stereotype.Component;

@Component
public class RegionMapper {

    public RegionEntity createEntity(Long regionId) {
        return RegionEntity.builder()
                .id(regionId)
                .build();
    }

    public RegionEntity createEntity(RegisterRegionRequest request) {
        return RegionEntity.builder()
                .name(request.getName())
                .del(0)
                .build();
    }

    public RegionDTO toDTO(RegionEntity regionEntity){
        return RegionDTO.builder()
                .id(regionEntity.getId())
                .name(regionEntity.getName())
                .build();
    }

}
