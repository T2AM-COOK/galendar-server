package com.galendar.domain.contest.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContestCost {
    FREE("FREE"),
    PAID("PAID");
    private final String key;
}