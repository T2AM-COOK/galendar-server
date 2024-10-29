package com.galendar.domain.email.controller;

import com.galendar.domain.email.dto.request.EmailSendRequest;
import com.galendar.domain.email.dto.request.EmailVerifyRequest;
import com.galendar.domain.email.service.EmailVerificationService;
import com.galendar.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "이메일", description = "이메일 관련 api입니다.")
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailVerificationService emailVerificationService;

    @Operation(summary = "이메일 전송")
    @PostMapping("/send")
    public ResponseData<Object> sendVerificationCode(@Valid @RequestBody EmailSendRequest request) {
        emailVerificationService.sendVerificationCode(request.getEmail());
        return ResponseData.ok(null, "이메일이 전송되었습니다.");
    }

    @Operation(summary = "이메일 인증")
    @PostMapping("/verify")
    public ResponseData<Object> verifyEmail(@Valid @RequestBody EmailVerifyRequest request) {
        emailVerificationService.verifyCode(request.getEmail(), request.getCode());
        return ResponseData.ok(null, "이메일이 인증되었습니다.");
    }
}