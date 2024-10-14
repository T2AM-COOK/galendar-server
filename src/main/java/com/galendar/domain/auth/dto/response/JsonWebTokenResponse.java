package com.galendar.domain.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class JsonWebTokenResponse {
    private String accessToken;
    private String refreshToken;
}
