package com.galendar.domain.firebase.repository;

import com.galendar.domain.firebase.dto.FcmMessage;
import com.galendar.domain.firebase.entity.FcmTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FcmTokenRepository extends JpaRepository<FcmTokenEntity, Long> {
    // 토큰 중복 확인을 위한 메서드
    boolean existsByToken(String token);
    FcmTokenEntity findByToken(String token);
}