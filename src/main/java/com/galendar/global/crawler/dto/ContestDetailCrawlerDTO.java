package com.galendar.global.crawler.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ContestDetailCrawlerDTO {
    private String targets;
    private String regions;
    private String cost;
    private LocalDate submitStartDate, submitEndDate;
    private LocalDate contestStartDate, contestEndDate;
}
