package com.galendar.domain.user.repository;

import com.galendar.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByName(String name);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByName(String name);
}