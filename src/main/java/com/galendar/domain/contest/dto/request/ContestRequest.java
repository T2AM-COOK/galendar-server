package com.galendar.domain.contest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContestRequest {
    private int page;
    private int size;
    private String keyword;
    private List<Long> targets;
    private List<Long> regions;
    public ContestRequest() {
        page = 1;
        size = 20;
    }

}
