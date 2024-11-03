package com.galendar.global.scheduler;

import com.galendar.domain.email.repository.EmailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final EmailRepository emailRepository;

    @Scheduled(cron = "0 0 * * * *")
    @Transactional
    public void deleteExpiredEmails() {
        emailRepository.deleteByExpirationDateBeforeAndIsVerifiedFalse(LocalDateTime.now());
    }

}
