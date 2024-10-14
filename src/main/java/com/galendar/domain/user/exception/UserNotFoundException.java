package com.galendar.domain.user.exception;

import com.galendar.global.exception.CustomException;

public class UserNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(400, "회원을 찾을 수 없습니다.");
    }
}
