package com.galendar.domain.auth.controller;

import com.galendar.domain.auth.dto.request.AuthenticationRequest;
import com.galendar.domain.auth.dto.request.RefreshTokenRequest;
import com.galendar.domain.auth.dto.request.SignupRequest;
import com.galendar.domain.auth.dto.response.JsonWebTokenResponse;
import com.galendar.domain.auth.service.AuthService;
import com.galendar.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증", description = "인증")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "회원 가입", description = "회원 가입")
    @PostMapping("/signup")
    public ResponseEntity<ResponseData> signup(@Validated @RequestBody SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.ok(ResponseData.ok("가입이 완료되었습니다!"));
    }

    @Operation(summary = "회원 인증", description = "회원 인증")
    @PostMapping
    public ResponseEntity<ResponseData> auth(@Validated @RequestBody AuthenticationRequest request) {
        log.info(request.getEmail());
        ResponseData<JsonWebTokenResponse> responseData = ResponseData.ok(authService.auth(request), "로그인 성공");
        return ResponseEntity.ok(responseData);
    }

    @Operation(summary = "토큰 재발급", description = "토근 재발급")
    @PostMapping("/refresh")
    public ResponseEntity<ResponseData> refresh(@Validated @RequestBody RefreshTokenRequest request) {
        ResponseData<JsonWebTokenResponse> responseData = ResponseData.ok(authService.refresh(request), "재발급 성공");
        return ResponseEntity.ok(responseData);
    }
}
