package com.galendar.domain.auth.controller;

import com.galendar.domain.auth.dto.request.AuthenticationRequest;
import com.galendar.domain.auth.dto.request.RefreshTokenRequest;
import com.galendar.domain.auth.dto.request.SignupRequest;
import com.galendar.domain.auth.dto.response.JsonWebTokenResponse;
import com.galendar.domain.auth.service.AuthService;
import com.galendar.domain.auth.service.AuthServiceImpl;
import com.galendar.domain.user.dto.User;
import com.galendar.domain.user.entity.UserEntity;
import com.galendar.domain.user.repository.UserRepository;
import com.galendar.global.common.dto.response.ResponseData;
import com.galendar.global.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "인증", description = "인증 관련 api입니다.")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "회원 가입", description = "회원가입을 처리합니다.")
    @PostMapping("/signup")
    public ResponseEntity<ResponseData<String>> signup(@Validated @RequestBody SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.ok(ResponseData.ok("가입이 완료되었습니다!"));
    }

    @Operation(summary = "로그인", description = "로그인을 통해 인증을 처리합니다.")
    @PostMapping
    public ResponseEntity<ResponseData<JsonWebTokenResponse>> auth(@Validated @RequestBody AuthenticationRequest request) {
        JsonWebTokenResponse tokenResponse = authService.auth(request);
        return ResponseEntity.ok(ResponseData.ok(tokenResponse, "로그인 성공"));
    }

    @Operation(summary = "토큰 재발급", description = "리프레시 토큰을 이용해 새로운 액세스 토큰을 재발급합니다.")
    @PostMapping("/refresh")
    public ResponseEntity<ResponseData<JsonWebTokenResponse>> refresh(@Validated @RequestBody RefreshTokenRequest request) {
        JsonWebTokenResponse tokenResponse = authService.refresh(request);
        return ResponseEntity.ok(ResponseData.ok(tokenResponse, "재발급 성공"));
    }

    @Operation(summary = "사용자 정보 조회", description = "")
    @GetMapping("/me")
    public String getCurrentUser(Principal principal) {
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) principal;
            User user = customUserDetails.getUser();
        }
    }
}