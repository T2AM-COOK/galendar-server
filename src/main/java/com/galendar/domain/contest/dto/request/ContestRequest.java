package com.galendar.domain.contest.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ContestRequest {
    private int page;
    private int size;
    private String keyword;
    private List<Long> targets;
    private List<Long> regions;
    private LocalDate SubmitStartDate;
    private LocalDate SubmitEndDate;
    public ContestRequest() {
        page = 1;
        size = 20;
    }

}
