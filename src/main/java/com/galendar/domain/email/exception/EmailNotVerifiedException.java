package com.galendar.domain.email.exception;

import com.galendar.domain.user.exception.UserNotFoundException;
import com.galendar.global.exception.CustomException;

public class EmailNotVerifiedException extends CustomException {
    public static final CustomException EXCEPTION = new EmailNotVerifiedException();

    private EmailNotVerifiedException() {
        super(400, "인증된 회원을 찾을 수 없습니다.");
    }
}
