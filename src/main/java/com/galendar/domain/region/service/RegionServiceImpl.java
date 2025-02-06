package com.galendar.domain.region.service;

import com.galendar.domain.region.dto.RegionDTO;
import com.galendar.domain.region.dto.request.RegisterRegionRequest;
import com.galendar.domain.region.mapper.RegionMapper;
import com.galendar.domain.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    public void register(RegisterRegionRequest request) {
        regionRepository.save(regionMapper.createEntity(request));
    }

    @Override
    public List<RegionDTO> list() {
        return regionRepository.findActive().stream().map(regionMapper::toDTO).toList();
    }
}
