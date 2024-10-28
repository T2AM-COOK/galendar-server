package com.galendar.domain.contest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContestDTO {
    private Long memberId;
    private String title;
    private String content;
    private String cost;
    private String link;
    private String imgLink;
    private String submitStartDate;
    private String submitEndDate;
    private String contestStartDate;
    private String contestEndDate;
}
