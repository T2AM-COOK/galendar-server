package com.galendar.domain.contest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class ContestDeadlineResponse {
    private Long id;
    private String title;
    private String email;
    private LocalDate submitStartDate;
    private LocalDate submitEndDate;
}
