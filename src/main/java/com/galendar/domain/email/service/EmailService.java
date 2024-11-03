package com.galendar.domain.email.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.galendar.domain.email.repository.EmailRepository;
import com.galendar.global.config.MailConfig;
import com.galendar.global.exception.CustomException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender javaMailSender;
    private final MailConfig mailConfig;
    private final EmailRepository emailRepository;

    public void sendEmail(String email, String code) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            message.setFrom(mailConfig.getUsername());
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("갈랜더 이메일 인증코드 : " + code);

            ClassPathResource resource = new ClassPathResource("/static/email.html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
            String content = FileCopyUtils.copyToString(reader);
            content = content.replace("{code}", code);

            helper.setText(content, true);

            helper.addInline("image", new ClassPathResource("static/galendar.png"));

            javaMailSender.send(message);
        } catch (MessagingException | IOException e) {
            throw new CustomException(400, String.format("%s에게 인증 이메일 전송 실패: %s", email, e.getMessage()));
        } catch (Exception e) {
            throw new CustomException(400, e.getMessage());
        }
    }

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void deleteExpiredEmails() {
        emailRepository.deleteByExpirationDateBeforeAndIsVerifiedFalse(LocalDateTime.now());
    }
}