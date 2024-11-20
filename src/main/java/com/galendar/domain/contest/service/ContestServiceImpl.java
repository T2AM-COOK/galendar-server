package com.galendar.domain.contest.service;

import com.galendar.domain.contest.dto.request.RegisterContestRequest;
import com.galendar.domain.contest.entity.ContestEntity;
import com.galendar.domain.contest.entity.ContestRegionEntity;
import com.galendar.domain.contest.entity.ContestTargetEntity;
import com.galendar.domain.contest.exception.ContestNotFoundException;
import com.galendar.domain.contest.mapper.ContestMapper;
import com.galendar.domain.contest.mapper.ContestRegionMapper;
import com.galendar.domain.contest.mapper.ContestTargetMapper;
import com.galendar.domain.contest.repository.ContestRegionRepository;
import com.galendar.domain.contest.repository.ContestRepository;
import com.galendar.domain.contest.repository.ContestTargetRepository;
import com.galendar.domain.region.entity.RegionEntity;
import com.galendar.domain.region.mapper.RegionMapper;
import com.galendar.domain.target.entity.TargetEntity;
import com.galendar.domain.target.mapper.TargetMapper;
import com.galendar.domain.user.entity.UserEntity;
import com.galendar.global.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ContestServiceImpl implements ContestService {

    private final UserSecurity userSecurity;
    private final ContestRepository contestRepository;
    private final ContestTargetRepository contestTargetRepository;
    private final ContestRegionRepository contestRegionRepository;

    private final ContestMapper contestMapper;
    private final TargetMapper targetMapper;
    private final RegionMapper regionMapper;

    public void register(RegisterContestRequest request) {
        register(request, userSecurity.getUserEntity());
    }

    public void register(RegisterContestRequest request, UserEntity userEntity) {
        final ContestEntity contestEntity = contestMapper.createEntity(request, userEntity);

        List<TargetEntity> targetEntities = request.getTargets().stream().distinct().map(targetMapper::createEntity).toList();
        List<ContestTargetEntity> contestTargetEntities = targetEntities.stream().map(targetEntity -> ContestTargetMapper.createEntity(contestEntity, targetEntity)).toList();

        List<RegionEntity> regionEntities = request.getRegions().stream().distinct().map(regionMapper::createEntity).toList();
        List<ContestRegionEntity> contestRegionEntities = regionEntities.stream().map(regionEntity -> ContestRegionMapper.createEntity(contestEntity, regionEntity)).toList();

        contestRepository.save(contestEntity);
        contestTargetRepository.saveAll(contestTargetEntities);
        contestRegionRepository.saveAll(contestRegionEntities);
    }

    @Override
    public void remove(Long id) {
        ContestEntity contestEntity = contestRepository.findById(id).orElseThrow(() -> ContestNotFoundException.EXCEPTION);
        contestTargetRepository.deleteByContestEntity(contestEntity);
        contestRegionRepository.deleteByContestEntity(contestEntity);
        contestRepository.delete(contestEntity);
    }

}













