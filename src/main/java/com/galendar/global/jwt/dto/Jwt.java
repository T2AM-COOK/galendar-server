package com.galendar.global.jwt.dto;

public record Jwt(
        String accessToken,
        String refreshToken
) {
}