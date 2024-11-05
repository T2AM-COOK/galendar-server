package com.galendar.global.scheduler;

import com.galendar.domain.contest.dto.request.RegisterContestRequest;
import com.galendar.domain.contest.entity.enums.ContestCost;
import com.galendar.domain.contest.repository.ContestRepository;
import com.galendar.domain.contest.service.ContestService;
import com.galendar.domain.region.entity.RegionEntity;
import com.galendar.domain.region.repository.RegionRepository;
import com.galendar.domain.target.entity.TargetEntity;
import com.galendar.domain.target.repository.TargetRepository;
import com.galendar.domain.user.entity.UserEntity;
import com.galendar.domain.user.exception.UserNotFoundException;
import com.galendar.domain.user.repository.UserRepository;
import com.galendar.global.crawler.ContestCrawler;
import com.galendar.global.crawler.dto.ContestCrawlerDTO;
import com.galendar.global.crawler.dto.ContestDetailCrawlerDTO;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContestScheduler {
    private final ContestCrawler contestCrawler;
    private final ContestService contestService;
    private final ContestRepository contestRepository;
    private final TargetRepository targetRepository;
    private final RegionRepository regionRepository;
    private final UserRepository userRepository;

    @Transactional
    @Scheduled(cron = "0 0 */4 * * *")
    public void task() throws IOException {
        final UserEntity adminUserEntity = userRepository.findByEmail("admin@galendar.com").orElseThrow(() -> UserNotFoundException.EXCEPTION);
        List<TargetEntity> targetEntities = targetRepository.findActive();
        List<RegionEntity> regionEntities = regionRepository.findActive();
        List<ContestCrawlerDTO> newContestList = contestCrawler.list();

        for (ContestCrawlerDTO contest : newContestList) {
            if (!contestRepository.existsByStrNo(contest.getStrNo())) { // 등록 여부
                Document viewDocument = contestCrawler.view(contest.getViewLink());
                ContestDetailCrawlerDTO contestDetailCrawlerDTO = contestCrawler.parseContestDetails(viewDocument); // 대회 상세 정보
                List<Long> targetIds = targetEntities.stream()
                        .filter(entity -> Arrays.stream(contestDetailCrawlerDTO.getTargets().split(",")).anyMatch(target -> entity.getName().equals(target)))
                        .map(target -> target.getId())
                        .collect(Collectors.toList()); // 대회 대상 ids
                List<Long> regionIds = regionEntities.stream()
                        .filter(region -> contestDetailCrawlerDTO.getRegions().equals(region.getName()))
                        .map(region -> region.getId())
                        .collect(Collectors.toList()); // 대회 지역 ids

                RegisterContestRequest registerContestRequest = new RegisterContestRequest();
                registerContestRequest.setStrNo(contest.getStrNo()); // 고유값
                registerContestRequest.setLink(contest.getViewLink()); // 상세 보기 링크
                registerContestRequest.setTitle(contestCrawler.extractViewTitle(viewDocument)); // 대회 타이틀
                registerContestRequest.setContent(contestCrawler.extractViewContent(viewDocument)); // 대회 내용
                registerContestRequest.setImgLink(contestCrawler.extractViewImageUrl(viewDocument)); // 대회 이미지 링크
                registerContestRequest.setCost(contestDetailCrawlerDTO.getCost().equals("무료 접수") ? ContestCost.FREE : ContestCost.PAID); // 비용
                registerContestRequest.setSubmitStartDate(contestDetailCrawlerDTO.getSubmitStartDate()); // 접수 시작일
                registerContestRequest.setSubmitEndDate(contestDetailCrawlerDTO.getSubmitEndDate()); // 접수 종료일
                registerContestRequest.setContestStartDate(contestDetailCrawlerDTO.getContestStartDate()); // 심사 시작일
                registerContestRequest.setContestEndDate(contestDetailCrawlerDTO.getContestEndDate()); // 심사 종료일
                registerContestRequest.setTargets(targetIds); // 대회 참가 대상자
                registerContestRequest.setRegions(regionIds); // 대회 지역

                contestService.register(registerContestRequest, adminUserEntity);
            }
        }
    }
}
