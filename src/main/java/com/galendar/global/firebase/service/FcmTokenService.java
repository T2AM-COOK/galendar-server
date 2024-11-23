package com.galendar.global.firebase.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class FcmTokenService {
    private static final String FCM_TOKEN_PREFIX = "fcmToken:";

    private final StringRedisTemplate redisTemplate;

    public void register(String email, String fcmToken) {
        if(!StringUtils.hasText(fcmToken)) return;
        redisTemplate.opsForValue().set(key(email), fcmToken);
    }

    public String get(String email) {
        return redisTemplate.opsForValue().get(key(email));
    }

    public void remove(String email) {
        String redisKey = FCM_TOKEN_PREFIX + email;
        redisTemplate.delete(redisKey);
    }

    private String key(String email) {
        return FCM_TOKEN_PREFIX + email;
    }

}
