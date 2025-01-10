package com.galendar.global.jwt.exception;

import com.galendar.global.exception.CustomException;

public class TokenTypeException extends CustomException {
    public static final CustomException EXCEPTION = new TokenTypeException();

    private TokenTypeException() {
        super(400, "잘못된 JWT 사용");
    }
}
