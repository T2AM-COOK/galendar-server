package com.galendar.domain.contest.dto.response;

import com.galendar.domain.contest.entity.enums.ContestCost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class ContestResponse {
    private Long id;
    private String title;
    private String content;
    private ContestCost cost;
    private String link;
    private String imgLink;
    private boolean isBookmarked;
    private LocalDate submitStartDate;
    private LocalDate submitEndDate;
    private LocalDate contestStartDate;
    private LocalDate contestEndDate;
}
