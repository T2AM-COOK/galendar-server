package com.galendar.domain.auth.service;

import com.galendar.domain.auth.dto.request.AuthenticationRequest;
import com.galendar.domain.auth.dto.request.RefreshTokenRequest;
import com.galendar.domain.auth.dto.request.SignupRequest;
import com.galendar.domain.auth.dto.response.JsonWebTokenResponse;

public interface AuthService {
    void signup(SignupRequest request);
    JsonWebTokenResponse auth(AuthenticationRequest request);
    JsonWebTokenResponse refresh(RefreshTokenRequest request);
}
