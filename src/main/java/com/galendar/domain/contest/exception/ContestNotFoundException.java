package com.galendar.domain.contest.exception;

import com.galendar.global.exception.CustomException;

public class ContestNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new ContestNotFoundException();

    private ContestNotFoundException() {
        super(400, "해당 대회 정보를 찾을 수 없습니다.");
    }
}