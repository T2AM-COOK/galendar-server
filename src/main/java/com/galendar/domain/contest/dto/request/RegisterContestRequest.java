package com.galendar.domain.contest.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.galendar.domain.contest.entity.enums.ContestCost;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class RegisterContestRequest {
    @NotBlank(message = "제목을 입력해 주세요")
    private String title; // 대회 제목
    @NotBlank(message = "내용을 입력해 주세요")
    private String content; // 대회 내용
    private ContestCost cost; // 대회 비용 (ENUM으로 처리)
    private String link; // 관련 링크
    private String imgLink; // 이미지 링크
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate submitStartDate; // 신청 시작일
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate submitEndDate; // 신청 종료일
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate contestStartDate; // 대회 시작일
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate contestEndDate; // 대회 종료일

    @NotNull(message = "대상 id를 입력해 주세요")
    private List<Long> targets;

    @NotNull(message = "지역 id를 입력해 주세요")
    private List<Long> regions;

    private String strNo;
}