package com.galendar.domain.email.repository;

import com.galendar.domain.email.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailRepository extends JpaRepository<EmailEntity, Long> {

    Optional<EmailEntity> findByEmail(String email);

    Optional<EmailEntity> findByEmailAndVerificationCode(String email, String code);

    void deleteByExpirationDateBeforeAndIsVerifiedFalse(LocalDateTime expirationDate);

}