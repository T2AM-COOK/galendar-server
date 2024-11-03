package com.galendar.domain.email.service;

public interface EmailVerificationService {
    void sendVerificationCode(String email);
    void verifyCode(String email, String code);

}
