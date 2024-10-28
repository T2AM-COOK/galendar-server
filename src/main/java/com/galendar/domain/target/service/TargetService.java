package com.galendar.domain.target.service;

import com.galendar.domain.region.dto.RegionDTO;
import com.galendar.domain.target.dto.TargetDTO;
import com.galendar.domain.target.dto.request.RegisterTargetRequest;

import java.util.List;

public interface TargetService {
    void register(RegisterTargetRequest request);

    List<TargetDTO> list();

}
