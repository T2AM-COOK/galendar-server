package com.galendar.domain.contest.dto.response;

import com.galendar.domain.contest.entity.enums.ContestCost;
import com.galendar.domain.region.dto.RegionDTO;
import com.galendar.domain.target.dto.TargetDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class ContestDetailResponse {
    private Long id;
    private String title;
    private String content;
    private ContestCost cost;
    private String link;
    private String imgLink;
    private LocalDate submitStartDate;
    private LocalDate submitEndDate;
    private LocalDate contestStartDate;
    private LocalDate contestEndDate;
    private boolean isBookmarked;
    private Set<TargetDTO> targets ;
    private Set<RegionDTO> regions ;
}
