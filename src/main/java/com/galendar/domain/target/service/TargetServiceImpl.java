package com.galendar.domain.target.service;

import com.galendar.domain.target.dto.TargetDTO;
import com.galendar.domain.target.dto.request.RegisterTargetRequest;
import com.galendar.domain.target.mapper.TargetMapper;
import com.galendar.domain.target.repository.TargetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TargetServiceImpl implements TargetService {

    private final TargetRepository targetRepository;
    private final TargetMapper targetMapper;
    public void register(RegisterTargetRequest request){
        targetRepository.save(targetMapper.createEntity(request));
    }

    @Override
    public List<TargetDTO> list() {
        return targetRepository.findActive().stream().map(targetMapper::toDTO).toList();
    }

}
