package com.galendar.domain.contest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContestDto {
    private Long memberId; // 회원 ID
    private String title; // 대회 제목
    private String content; // 대회 내용
    private String cost; // 대회 비용 (ENUM으로 처리)
    private String link; // 관련 링크
    private String imgLink; // 이미지 링크
    private String submitStartDate; // 신청 시작일
    private String submitEndDate; // 신청 종료일
    private String contestStartDate; // 대회 시작일
    private String contestEndDate; // 대회 종료일
}