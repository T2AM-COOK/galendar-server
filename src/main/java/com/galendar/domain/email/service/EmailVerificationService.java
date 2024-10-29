package com.galendar.domain.email.service;

import com.galendar.domain.email.entity.EmailEntity;
import com.galendar.domain.email.repository.EmailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private static final int VERIFICATION_CODE_LENGTH = 6;
    private static final int VERIFICATION_CODE_UPPER_BOUND = 999999;
    private static final int VERIFICATION_CODE_LOWER_BOUND = 100000;
    private static final int EXPIRATION_MINUTES = 5;

    private final EmailRepository emailRepository;
    private final EmailService emailService;

    @Transactional
    public void sendVerificationCode(String email) {

        String code = generateVerificationCode();
            LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES);

            EmailEntity emailVerification = EmailEntity.builder()
                    .email(email)
                    .verificationCode(code)
                    .expirationDate(expirationDate)
                    .isVerified(false)
                    .build();

            emailRepository.save(emailVerification);

            emailService.sendEmail(email, code);
    }

    private String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        int code = random.nextInt(VERIFICATION_CODE_UPPER_BOUND - VERIFICATION_CODE_LOWER_BOUND + 1) + VERIFICATION_CODE_LOWER_BOUND;
        return String.format("%0" + VERIFICATION_CODE_LENGTH + "d", code);
    }

    @Transactional
    public void verifyCode(String email, String code) {
        EmailEntity verification = emailRepository.findByEmailAndVerificationCode(email, code).orElse(null);

            verification.setVerified(true);
            verification.setVerificationCode(null);
            verification.setExpirationDate(null);
            emailRepository.save(verification);

    }
}
