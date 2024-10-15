package com.galendar.domain.auth.repository;

import com.galendar.domain.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Boolean existsByUserName(String userName);

    UserEntity findByUserName(String userName);
}