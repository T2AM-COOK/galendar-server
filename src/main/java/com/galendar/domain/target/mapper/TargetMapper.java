package com.galendar.domain.target.mapper;

import com.galendar.domain.target.dto.TargetDTO;
import com.galendar.domain.target.dto.request.RegisterTargetRequest;
import com.galendar.domain.target.entity.TargetEntity;
import org.springframework.stereotype.Component;

@Component
public class TargetMapper {

    public TargetEntity createEntity(Long targetId) {
        return TargetEntity.builder()
                .id(targetId)
                .build();
    }

    public TargetEntity createEntity(RegisterTargetRequest request) {
        return TargetEntity.builder()
                .name(request.getName())
                .del(0)
                .build();
    }

    public TargetDTO toDTO(TargetEntity targetEntity) {
        return TargetDTO.builder()
                .id(targetEntity.getId())
                .name(targetEntity.getName())
                .build();
    }

}
