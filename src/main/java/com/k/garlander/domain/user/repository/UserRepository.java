package com.k.garlander.domain.user.repository;

import com.k.garlander.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Boolean existsByEmail(String userName);

    UserEntity findByEmail(String userName);
}