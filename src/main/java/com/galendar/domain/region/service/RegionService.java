package com.galendar.domain.region.service;

import com.galendar.domain.region.dto.RegionDTO;
import com.galendar.domain.region.dto.request.RegisterRegionRequest;

import java.util.List;

public interface RegionService {
    void register(RegisterRegionRequest request);
    List<RegionDTO> list();
}
