package com.galendar.domain.contest.service;

import com.galendar.domain.contest.dto.ContestDto;
import com.galendar.domain.contest.entity.ContestEntity;
import com.galendar.domain.contest.enums.ContestCost;
import com.galendar.domain.contest.repository.ContestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContestService {
    private final ContestRepository contestRepository;

    public void registerContest(ContestDto contestDto) {
        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setMemberId(contestDto.getMemberId());
        contestEntity.setTitle(contestDto.getTitle());
        contestEntity.setContent(contestDto.getContent());
        contestEntity.setCost(ContestCost.valueOf(contestDto.getCost())); // ENUM 변환
        contestEntity.setLink(contestDto.getLink());
        contestEntity.setImgLink(contestDto.getImgLink());
        contestEntity.setSubmitStartDate(LocalDateTime.parse(contestDto.getSubmitStartDate()));
        contestEntity.setSubmitEndDate(LocalDateTime.parse(contestDto.getSubmitEndDate()));
        contestEntity.setContestStartDate(LocalDateTime.parse(contestDto.getContestStartDate()));
        contestEntity.setContestEndDate(LocalDateTime.parse(contestDto.getContestEndDate()));
        contestEntity.setCreatedDate(LocalDateTime.now());
        contestEntity.setModifiedDate(LocalDateTime.now());

        contestRepository.save(contestEntity);
    }

    public List<ContestDto> getAllContests() {
        // 데이터베이스에서 모든 대회 정보를 조회하여 DTO로 변환 후 반환
        return contestRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    private ContestDto toDto(ContestEntity contestEntity) {
        ContestDto contestDto = new ContestDto();
        contestDto.setMemberId(contestEntity.getMemberId());
        contestDto.setTitle(contestEntity.getTitle());
        contestDto.setContent(contestEntity.getContent());
        contestDto.setCost(contestEntity.getCost().name()); // ENUM을 String으로 변환
        contestDto.setLink(contestEntity.getLink());
        contestDto.setImgLink(contestEntity.getImgLink());
        contestDto.setSubmitStartDate(contestEntity.getSubmitStartDate().toString());
        contestDto.setSubmitEndDate(contestEntity.getSubmitEndDate().toString());
        contestDto.setContestStartDate(contestEntity.getContestStartDate().toString());
        contestDto.setContestEndDate(contestEntity.getContestEndDate().toString());
        return contestDto;
    }
}